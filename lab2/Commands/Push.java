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
                CommLogger.exeWarn(Push.class.getName());
                throw new IllegalArgumentException("Such variable isn't defined: " + args[1]);
            }
        }
        CommLogger.exeInfo(Push.class.getName());
    }

    @Override
    public Push get() {
        return new Push();
    }
}
