package game;

import controller.Controller;
import model.Model;
import view.View;

public class Game {
    private final Model model;

    public Game(){
        this.model = new Model();
        Controller controller = new Controller(model, this);
        new View(controller, model);
    }

    public void start(){
        model.start();
    }
}
