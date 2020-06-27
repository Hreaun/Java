package Server.controller;

import Server.model.Model;
import Server.model.Paddle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Thread implements Observer {
    final Model model;
    Socket current;
    int enemyID;
    int ID;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Controller(Socket current, Model model, int ID, int enemyID, ObjectInputStream in, ObjectOutputStream out) {
        this.current = current;
        this.model = model;
        this.enemyID = enemyID;
        this.ID = ID;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                synchronized (model.player[ID]) {
                    model.player[ID] = (Paddle) in.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            synchronized (model) {
                out.reset();
                out.writeObject(model.gameStatus);
                out.writeObject(model.puck);
                out.writeObject(model.player[ID]);
                out.writeObject(model.playerOne);
                out.writeObject(model.playerTwo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
