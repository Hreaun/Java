package Commands;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Sqrt implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'SQRT' doesn't require arguments.");
            if (stack.peek() < 0)
                throw new InvalidParameterException("Negative root");
            stack.push(Math.sqrt(stack.pop()));
        } catch (Exception ex) {
            System.out.println("An error at 'SQRT' command occurred.");
            throw ex;
        }
    }

    @Override
    public Sqrt get() {
        return new Sqrt();
    }
}
