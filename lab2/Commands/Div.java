package Commands;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Div implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) throws EmptyStackException, ArithmeticException {
        Double a;
        Double b;
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'/' doesn't require arguments.");
            if (stack.size() < 2)
                throw new IllegalArgumentException("Not enough parameters on stack.");
            a = stack.pop();
            b = stack.pop();
        } catch (Exception ex) {
            CommLogger.exeWarn(Div.class.getName());
            throw ex;
        }
        try {
            if (b == 0.0)
                throw new ArithmeticException("Division by zero.");
            stack.push(a / b);
        } catch (Exception ex) {
            CommLogger.exeWarn(Div.class.getName());
            throw ex;
        }
        CommLogger.exeInfo(Div.class.getName());
    }

    @Override
    public Div get() {
        return new Div();
    }
}
