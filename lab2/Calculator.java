import Commands.Commands;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private Commands command;
    private Stack<Double> stack;
    private HashMap<String, Double> def;

    public Calculator() {
        this.stack = new Stack<>();
        this.def = new HashMap<>();
    }

    public void setCommand(Commands com) {
        this.command = com;
    }

    public void exeCommand(String[] args) {
        command.execute(stack, def, args);
    }
}
