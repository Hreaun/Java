package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Push implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        if (def.containsKey(args[1])) {
            stack.push(def.get(args[1]));
        } else {
            try {
                stack.push(Double.parseDouble(args[1]));
            } catch (NumberFormatException ex) {
                System.out.println("An error at 'PUSH' command occurred.");
                throw new IllegalArgumentException("Such variable isn't defined: " + args[1]);
            }
        }
    }

    @Override
    public Push get() {
        return new Push();
    }
}