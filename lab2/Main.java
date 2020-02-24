import Commands.Commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.EmptyStackException;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    static Logger log;

    static {
        try (FileInputStream ins = new FileInputStream("C:\\Users\\troll\\IdeaProjects\\lab2\\src\\main\\lab2\\log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(Main.class.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static public void main(String[] argv) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Input in;
        if (argv.length == 0) {
            System.out.println("Write EXIT to quit.");
            in = new ByConsole();
        } else {
            try {
                in = new ByFile(argv[0]);
            } catch (FileNotFoundException ex) {
                log.logp(Level.SEVERE, "Main", "main", ex.getLocalizedMessage());
                return;
            }
        }

        Properties commands = new Properties();
        commands.load(new FileInputStream("C:\\Users\\troll\\IdeaProjects\\lab2\\src\\main\\lab2\\Commands\\commands.properties"));
        Calculator calc = new Calculator();

        while (true) {
            String[] str = in.read();
            if (str == null)
                break;
            try {
                Object command = Class.forName(commands.getProperty(str[0])).getDeclaredConstructor().newInstance();
                Factory.registerCommand(str[0], (Supplier<? extends Commands>) command);
            } catch (NullPointerException ex) {
                log.logp(Level.WARNING, "Main", "main", "Wrong command: " + str[0]);
                continue;
            }
            calc.setCommand(Factory.getCommand(str[0]));
            try {
                calc.exeCommand(str);
            } catch (EmptyStackException ex) {
                log.logp(Level.WARNING, "Main", "main", "There is nothing on stack.");
            } catch (Exception ex) {
                log.log(Level.WARNING, ex.getMessage());
            }

        }
    }
}
