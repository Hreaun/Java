import Commands.Commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.EmptyStackException;
import java.util.Properties;
import java.util.function.Supplier;


public class Main {
    static public void main(String[] argv) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Input in;
        if (argv.length == 0) {
            System.out.println("Write EXIT to quit.");
            in = new ByConsole();
        } else {
            try {
                in = new ByFile(argv[0]);
            } catch (FileNotFoundException ex) {
                return;
            }
        }

        Properties commands = new Properties();
        commands.load(new FileInputStream("src/main/java/Commands/commands.properties"));
        Calculator calc = new Calculator();

        while (true) {
            String[] str = in.read();
            if (str == null)
                break;
            try {
                Object command = Class.forName(commands.getProperty(str[0])).getDeclaredConstructor().newInstance();
                Factory.registerCommand(str[0], (Supplier<? extends Commands>) command);
            } catch (NullPointerException ex) {
                System.out.println("Wrong command.");
                continue;
            }
            calc.setCommand(Factory.getCommand(str[0]));
            try {
                calc.exeCommand(str);
            } catch (EmptyStackException ex) {
                System.out.println("There is nothing on stack.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
