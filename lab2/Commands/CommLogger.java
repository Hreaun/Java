package Commands;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CommLogger {
    static public Logger log;

    static {
        try (FileInputStream ins = new FileInputStream("C:\\Users\\troll\\IdeaProjects\\lab2\\src\\main\\lab2\\log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(Commands.class.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void exeInfo(String className) {
        CommLogger.log.logp(Level.INFO, className, "", className + " executed.");
    }

    static void exeWarn(String className) {
        CommLogger.log.logp(Level.WARNING, className, "", "An error at " + className + " occurred.");
    }
}
