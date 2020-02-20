package Commands;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class MulTest {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> def = new HashMap<>();
    String[] args = {"Mul"};
    Mul mul = new Mul();

    @Test
    public void execute() {
        stack.push(10d);
        stack.push(5d);
        mul.execute(stack, def, args);
        Assert.assertEquals(stack.peek(), (Double)50d);
    }
}