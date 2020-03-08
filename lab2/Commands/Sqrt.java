package Commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sqrt implements Commands {
    private static Logger log = Logger.getLogger(Sqrt.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'SQRT' doesn't require arguments.");
            if (stack.peek() < 0)
                throw new InvalidParameterException("Negative root");
            stack.push(Math.sqrt(stack.pop()));
        } catch (Exception ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
        log.log(Level.INFO, this.getClass().getName() + " executed, result: " + stack.peek());
    }

}
