package Server;

import Server.controller.Controller;
import Server.model.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private final ArrayList<Lobby> lobbies = new ArrayList<>();
    ServerSocket s;

    public void close(){
        for (Lobby lobby: lobbies) {
            lobby.close();
            lobby.interrupt();
        }
        try {
            s.close();
        } catch (IOException ignored) { }
    }

    @Override
    public void run() {
        try {
            s = new ServerSocket(2048);
        } catch (IOException e) {
            System.out.println("Port is busy");
            e.printStackTrace();
            return;
        }

        while (!isInterrupted()) {
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
                System.out.println("Server closed");
                return;
            }
            System.out.println("Player 1 connected.");

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
                System.out.println("Server closed");
                return;
            }
            System.out.println("Player 2 connected.");

            Lobby lobby = new Lobby(model, in1, out1, controller1, in2, out2, controller2);
            lobbies.add(lobby);
            lobby.start();
        }


    }
}
