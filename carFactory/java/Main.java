import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    static {
        try (InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties factorySettings = new Properties();
        try {
            factorySettings.load(Objects.requireNonNull(Thread.currentThread().
                    getContextClassLoader().getResourceAsStream("FactorySettings.properties")));
        } catch (IOException ioException) {
            log.severe(ioException.getMessage());
            return;
        }

        Factory factory = new Factory(Integer.parseInt(factorySettings.getProperty("Dealers")),
                Integer.parseInt(factorySettings.getProperty("EngineStorage")),
                Integer.parseInt(factorySettings.getProperty("BodyStorage")),
                Integer.parseInt(factorySettings.getProperty("AccessoryStorage")),
                Integer.parseInt(factorySettings.getProperty("AccessorySuppliers")),
                Integer.parseInt(factorySettings.getProperty("Workers")),
                Integer.parseInt(factorySettings.getProperty("CarStorage")), factorySettings);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to stop");

        factory.start();

        scanner.nextLine();

        factory.stop();

    }

}
