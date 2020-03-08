import Commands.Commands;

import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    private static Logger log = Logger.getLogger(Calculator.class.getName());

    private Stack<Double> stack = new Stack<>();
    private HashMap<String, Double> def;

    public Calculator() {
        this.def = new HashMap<>();
    }

    public void calculate(InputStreamReader in) {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] words = str.split("\\s+");
            if ((words[0].isEmpty()) || (words[0].charAt(0) == '#'))
                continue;
            if (words[0].equals("EXIT"))
                return;

            Commands command = Factory.getInstance().getCommand(words[0]);
            try {
                command.execute(stack, def, words);
            } catch (EmptyStackException ex) {
                log.log(Level.WARNING, "There is nothing on stack.");
            } catch (NullPointerException ignored) {
            } catch (Exception ex) {
                log.log(Level.WARNING, ex.getMessage());
            }

        }

    }
}
