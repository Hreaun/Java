package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Push implements Commands {
    private static Logger log = Logger.getLogger(Push.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        if (def.containsKey(args[1])) {
            stack.push(def.get(args[1]));
        } else {
            try {
                stack.push(Double.parseDouble(args[1]));
            } catch (NumberFormatException ex) {
                log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
                throw new IllegalArgumentException("Such variable isn't defined: " + args[1]);
            }
        }
        log.log(Level.INFO, this.getClass().getName() + " executed, result: " + stack.peek());
    }

}
