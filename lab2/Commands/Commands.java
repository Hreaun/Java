package Commands;

import java.util.HashMap;
import java.util.Stack;

public interface Commands {
    void execute(Stack<Double> stack, HashMap<String, Double> def, String[] args);
}
