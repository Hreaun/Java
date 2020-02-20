package Commands;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class PopTest {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> def = new HashMap<>();
    String[] args = {"Sqrt"};
    Pop pop = new Pop();

    @Test(expected = EmptyStackException.class)
    public void executeException() {
        pop.execute(stack, def, args);
    }
}