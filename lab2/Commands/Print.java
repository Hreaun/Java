package Commands;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Print implements Commands {
    private static Logger log = Logger.getLogger(Print.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("'PRINT' doesn't require arguments.");
        try {
            System.out.println(stack.peek());
        } catch (EmptyStackException ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
        log.log(Level.INFO, this.getClass().getName() + " executed.");
    }

}