package Server;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to close server");
        scanner.nextLine();
        server.close();
        server.interrupt();
        try {
            server.join();
        } catch (InterruptedException ignored) { }
    }
}