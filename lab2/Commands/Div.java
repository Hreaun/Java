package Commands;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Div implements Commands{
    private static Logger log = Logger.getLogger(Div.class.getName());

    @Override
    public void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args) throws EmptyStackException, ArithmeticException {
        Double a;
        Double b;
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("'/' doesn't require arguments.");
            if (stack.size() < 2)
                throw new NotEnoughParametersException("Not enough parameters on stack.");
            a = stack.pop();
            b = stack.pop();
        } catch (Exception ex) {
            log.log(Level.WARNING, "An error at " + this.getClass().getName() + " occurred");
            throw ex;
        }
        stack.push(a / b);
        log.log(Level.INFO, this.getClass().getName() + " executed, result: " + stack.peek());
    }
}
