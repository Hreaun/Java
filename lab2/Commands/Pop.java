package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pop implements Commands {
    private static Logger log = Logger.getLogger(Pop.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'POP' doesn't require arguments.");
            stack.pop();
        } catch (Exception ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
        log.log(Level.INFO, this.getClass().getName() + " executed.");
    }

}
