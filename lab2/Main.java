import java.io.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    static {
        try (InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static public void main(String[] argv) {
        InputStreamReader in;
        if (argv.length == 0) {
            System.out.println("Write EXIT to quit.");
            in = new InputStreamReader(System.in);
        } else {
            try {
                in = new InputStreamReader(new FileInputStream(argv[0]));
            } catch (FileNotFoundException ex) {
                log.log(Level.SEVERE, ex.getLocalizedMessage());
                return;
            }
        }

        Calculator calc = new Calculator();
        calc.calculate(in);
    }
}
