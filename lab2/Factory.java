import Commands.Commands;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class Factory {
    private static Logger log = Logger.getLogger(Factory.class.getName());
    private Properties commands = new Properties();

    private volatile static Factory instance = null;

    public static Factory getInstance() {
        if(instance == null){
            synchronized (Factory.class){
                if(instance == null){
                    instance = new Factory();
                }
            }
        }
        return instance;
    }

    private Factory() {
        try {
            commands.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("commands.properties")));
        }
        catch (IOException ex){
            log.log(Level.SEVERE, ex.getMessage());
        }
    }

    public Commands getCommand(String type) {
        Object command = null;
        try {
            if(!commands.containsKey(type)){
                throw new InvalidParameterException("Wrong command: " + type);
            }
            command = Class.forName(commands.getProperty(type)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return (Commands) command;
    }
}