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

    public Client(Controller controller, Socket socket) throws IOException {
        this.controller = controller;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
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
            ex.printStackTrace();
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

