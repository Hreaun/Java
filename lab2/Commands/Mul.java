package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Mul implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'*' doesn't require arguments.");
            stack.push(stack.pop() * stack.pop());
        } catch (Exception ex) {
            CommLogger.exeWarn(Mul.class.getName());
            throw ex;
        }
        CommLogger.exeInfo(Mul.class.getName());
    }

    @Override
    public Mul get() {
        return new Mul();
    }
}