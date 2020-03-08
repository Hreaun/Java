package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements Commands {
    private static Logger log = Logger.getLogger(Define.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) throws IllegalArgumentException {
        try {
            if (args.length != 3)
                throw new IllegalArgumentException("'DEFINE' requires 2 arguments.");
            try {
                Double.parseDouble(args[1]);
            } catch (NumberFormatException ex) {
                def.put(args[1], Double.valueOf(args[2]));
                log.log(Level.INFO, this.getClass().getName() + " executed, result: " + args[1] + " = " + def.get(args[1]));
                return;
            }
            throw new IllegalArgumentException("Cannot define a number.");
        } catch (IllegalArgumentException ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
    }

}
