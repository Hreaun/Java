package game;

import controller.Controller;
import model.Model;
import view.View;

public class Game {
    private final Model model;

    public Game(){
        this.model = new Model();
        Controller controller = new Controller(model, this);
        View view = new View(controller, model);
        model.addObserver(view.renderer);
        model.timer.start();
    }

    public void start(){
        model.start();
    }
}
