package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add implements Commands {
    private static Logger log = Logger.getLogger(Add.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'+' doesn't require arguments.");
            if (stack.size() < 2)
                throw new NotEnoughParametersException("Not enough parameters on stack.");
            stack.push(stack.pop() + stack.pop());
        } catch (Exception ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
        log.log(Level.INFO, this.getClass().getName() + " executed, result: " + stack.peek());
    }
}
