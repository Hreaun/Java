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
    public static void main(String[] args) {
        ServerSocket s;
        try {
            s = new ServerSocket(2048, 2);
        } catch (IOException e) {
            System.out.println("Port is busy");
            e.printStackTrace();
            return;
        }
        Model model = new Model();


        Socket p1;
        ObjectInputStream in1;
        ObjectOutputStream out1;
        Controller controller1;
        try {
            p1 = s.accept();
            in1 = new ObjectInputStream(p1.getInputStream());
            out1 = new ObjectOutputStream(p1.getOutputStream());
            controller1 = new Controller(p1, model, 0, 1, in1, out1);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Player 1 connected.");


        try {
            out1.writeObject(model);
            out1.reset();
            out1.writeObject(model.playerOne);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Socket p2;
        ObjectInputStream in2;
        ObjectOutputStream out2;
        Controller controller2;
        try {
            p2 = s.accept();
            in2 = new ObjectInputStream(p2.getInputStream());
            out2 = new ObjectOutputStream(p2.getOutputStream());
            controller2 = new Controller(p2, model, 1, 0, in2, out2);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Player 2 connected.");


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
        } catch (InterruptedException ignored) {

        }

    }
}