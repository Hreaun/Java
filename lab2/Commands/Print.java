package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Print implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("'PRINT' doesn't require arguments.");
        if (!stack.empty())
            System.out.println(stack.peek());
    }

    @Override
    public Print get() {
        return new Print();
    }
}