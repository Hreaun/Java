package Server;


import Server.controller.Controller;
import Server.model.Model;
import Server.model.Paddle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket s = new ServerSocket(2048, 2);
        Model model = new Model();

        Socket p1 = s.accept();
        System.out.println("Player 1 connected.");
        ObjectInputStream in1 = new ObjectInputStream(p1.getInputStream());
        ObjectOutputStream out1 = new ObjectOutputStream(p1.getOutputStream());
        Controller controller1 = new Controller(p1, model, 0, 1, in1, out1);


        out1.writeObject(model);
        out1.reset();
        out1.writeObject(model.playerOne);

        Socket p2 = s.accept();
        System.out.println("Player 2 connected.");
        ObjectInputStream in2 = new ObjectInputStream(p2.getInputStream());
        ObjectOutputStream out2 = new ObjectOutputStream(p2.getOutputStream());
        Controller controller2 = new Controller(p2, model, 1, 0, in2, out2);

        out2.writeObject(model);
        out2.reset();
        out2.writeObject(model.playerTwo);


        while (true) {
            if (((Paddle)in1.readObject()).ready && ((Paddle)in2.readObject()).ready)
                break;
        }

        model.addObserver(controller1);
        model.addObserver(controller2);

        model.start();
        controller1.start();
        controller2.start();
    }
}