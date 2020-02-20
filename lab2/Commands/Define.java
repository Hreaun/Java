package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Define implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) throws IllegalArgumentException {
        try {
            if (args.length != 3)
                throw new IllegalArgumentException("'DEFINE' requires 2 arguments.");
            try {
                Double.parseDouble(args[1]);
            } catch (NumberFormatException ex) {
                def.put(args[1], Double.valueOf(args[2]));
                return;
            }
            throw new IllegalArgumentException("Cannot define a number.");
        } catch (IllegalArgumentException ex) {
            System.out.println("An error at 'DEFINE' command occurred.");
            throw ex;
        }

    }

    @Override
    public Define get() {
        return new Define();
    }

}
