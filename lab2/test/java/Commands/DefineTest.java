package Commands;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class DefineTest {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> def = new HashMap<>();
    String[] args = {"Define", "-4", "56"};
    Define define = new Define();

    @Test(expected = IllegalArgumentException.class)
    public void executeException() {
        define.execute(stack, def, args);
    }
}