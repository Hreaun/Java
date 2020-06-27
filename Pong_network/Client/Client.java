package Client;

import Client.controller.Controller;
import Client.view.View;
import Server.model.GameStatus;
import Server.model.Model;
import Server.model.Paddle;
import Server.model.Puck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread implements Observer {
    Socket socket;
    Controller controller;
    ObjectOutputStream out;
    ObjectInputStream in;

    public Client(Controller controller, Socket socket)  {
        this.controller = controller;
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Disconnect");
        }
    }

    @Override
    public void run() {
        try {
            controller.model = (Model) in.readObject();
            new View(controller);
            controller.model.changed();
            controller.model.notifyObservers();
            controller.paddle = (Paddle) in.readObject();

            while (!isInterrupted()) {
                controller.model.gameStatus = (GameStatus) in.readObject();
                controller.model.puck = (Puck) in.readObject();
                controller.paddle = (Paddle) in.readObject();
                controller.model.playerOne = (Paddle) in.readObject();
                controller.model.playerTwo = (Paddle) in.readObject();
                controller.model.changed();
                controller.model.notifyObservers();
            }
        } catch (IOException | ClassNotFoundException ex) {
            if (controller.model == null){
                System.out.println("Disconnect");
                return;
            }
            controller.model.gameStatus = GameStatus.DISCONNECT;
            controller.model.changed();
            controller.model.notifyObservers();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            out.reset();
            out.writeObject(controller.paddle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

