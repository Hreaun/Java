package Client;

import Client.controller.Controller;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 2048);

        Controller controller = new Controller();
        Client client = new Client(controller, s);
        controller.addObserver(client);
        client.start();
    }
}