package Commands;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Supplier;

public class Add implements Commands, Supplier<Commands> {
    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'+' doesn't require arguments.");
            stack.push(stack.pop() + stack.pop());
        } catch (Exception ex) {
            CommLogger.exeWarn(Add.class.getName());
            throw ex;
        }
        CommLogger.exeInfo(Add.class.getName());
    }

    @Override
    public Add get() {
        return new Add();
    }
}
