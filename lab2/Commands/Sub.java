package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Sub implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        Double a;
        Double b;
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'-' doesn't require arguments.");
            a = stack.pop();
            b = stack.pop();
        } catch (Exception ex) {
            CommLogger.exeWarn(Sub.class.getName());
            throw ex;
        }
        stack.push(a - b);
        CommLogger.exeInfo(Sub.class.getName());
    }

    @Override
    public Sub get() {
        return new Sub();
    }
}