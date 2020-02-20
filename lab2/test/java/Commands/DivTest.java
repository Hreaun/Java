package Commands;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class DivTest {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> def = new HashMap<>();
    String[] args = {"Div"};
    Div div = new Div();

    @Test(expected = ArithmeticException.class)
    public void executeException() {
        stack.push(0d);
        stack.push(5d);
        div.execute(stack, def, args);
    }
}