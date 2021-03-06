package Server.controller;

import Server.model.GameStatus;
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
                if (model.gameStatus == GameStatus.DISCONNECT){
                    current.close();
                    return;
                }
                synchronized (model.player[ID]) {
                    model.player[ID] = (Paddle) in.readObject();
                    if (model.gameStatus == GameStatus.END) {
                        if (model.player[ID].ready && model.player[enemyID].ready) {
                            model.player[ID].reset();
                            model.player[enemyID].reset();
                            model.start();
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            model.deleteObserver(this);
            model.end();
            this.interrupt();
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
            model.deleteObserver(this);
            model.end();
            this.interrupt();
        }
    }
}
