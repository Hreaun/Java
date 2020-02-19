package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Pop implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'POP' doesn't require arguments.");
            stack.pop();
        } catch (Exception ex) {
            System.out.println("An error at 'POP' command occurred.");
            throw ex;
        }

    }

    @Override
    public Pop get() {
        return new Pop();
    }
}
