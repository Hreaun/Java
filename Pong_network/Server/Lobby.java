package Server;


import Server.controller.Controller;
import Server.model.GameStatus;
import Server.model.Model;
import Server.model.Paddle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Lobby extends Thread {
    Model model;
    ObjectInputStream in1;
    ObjectOutputStream out1;
    Controller controller1;
    ObjectInputStream in2;
    ObjectOutputStream out2;
    Controller controller2;

    public Lobby(Model model, ObjectInputStream in1, ObjectOutputStream out1, Controller controller1,
                 ObjectInputStream in2, ObjectOutputStream out2, Controller controller2) {
        this.model = model;
        this.in1 = in1;
        this.out1 = out1;
        this.controller1 = controller1;
        this.in2 = in2;
        this.out2 = out2;
        this.controller2 = controller2;
    }

    public void close(){
        model.gameStatus = GameStatus.DISCONNECT;
        model.changed();
        model.notifyObservers();
    }

    @Override
    public void run() {
        try {
            out1.writeObject(model);
            out1.reset();
            out1.writeObject(model.playerOne);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            out2.writeObject(model);
            out2.reset();
            out2.writeObject(model.playerTwo);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                if (((Paddle) in1.readObject()).ready && ((Paddle) in2.readObject()).ready)
                    break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        model.addObserver(controller1);
        model.addObserver(controller2);

        model.start();
        controller1.start();
        controller2.start();
        try {
            controller1.join();
            controller2.join();
        } catch (InterruptedException e) {
            close();
        }


    }
}
