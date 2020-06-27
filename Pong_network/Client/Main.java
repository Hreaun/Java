package Client;

import Client.controller.Controller;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Socket s = null;
        Client client = null;
        Controller controller = new Controller();
        try {
            s = new Socket("127.0.0.1", 2048);
            client = new Client(controller, s);
        } catch (IOException e) {
            System.out.println("Cannot connect");
            return;
        }
        controller.addObserver(client);
        client.start();
    }
}