package Commands;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class SqrtTest {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> def = new HashMap<>();
    String[] args = {"Sqrt"};
    Sqrt sqrt = new Sqrt();

    @Test(expected = InvalidParameterException.class)
    public void executeException() {
        stack.push(-5d);
        sqrt.execute(stack, def, args);
    }
}