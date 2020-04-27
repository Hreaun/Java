import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class Factory extends Thread{
    public static Properties factorySettings = new Properties();

    ArrayList<Dealer> dealers;
    Storage<Car> carStorage;
    Storage<Engine> engineStorage;
    Storage<Body> bodyStorage;
    Storage<Accessory> accessoryStorage;
    Supplier<Engine> engineSupplier;
    Supplier<Body> bodySupplier;
    ArrayList<Supplier<Accessory>> accessorySuppliers;
    ArrayList<Worker> workers;
    Controller controller;

    public Factory(int dealers, int engine, int body, int accessory, int accessorySuppliers, int workers, int cars){
        this.dealers = new ArrayList<>(dealers);
        this.engineStorage = new Storage<>(engine);
        this.bodyStorage = new Storage<>(body);
        this.accessoryStorage = new Storage<>(accessory);
        this.accessorySuppliers = new ArrayList<>(accessorySuppliers);
        this.workers =  new ArrayList<>(workers);
        this.carStorage = new Storage<>(cars);
        engineSupplier = new Supplier<>(engineStorage, Engine.class);
        bodySupplier = new Supplier<>(bodyStorage, Body.class);
        controller = new Controller(carStorage, this.workers);
    }

    @Override
    public void run() {
        int dealerAmount = getInt("Dealers");
        for (int i = 0; i < dealerAmount; i++) {
            dealers.add(new Dealer(carStorage, Integer.parseInt(factorySettings.getProperty("CarsForOneDealer")), i));
            dealers.get(i).start();
        }

        int accSuppAmount = getInt("AccessorySuppliers");
        for (int i = 0; i < accSuppAmount; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class));
            accessorySuppliers.get(i).start();
        }

        int workerAmount = getInt("Workers");
        for (int i = 0; i < workerAmount; i++) {
            workers.add(new Worker(carStorage, engineStorage, bodyStorage, accessoryStorage));
            workers.get(i).start();
        }

        engineSupplier.start();
        bodySupplier.start();
        controller.start();

        for (Dealer dealer: dealers) {
            try {
                dealer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        engineSupplier.interrupt();
        bodySupplier.interrupt();
        controller.interrupt();
        for (Supplier<Accessory> supplier:accessorySuppliers) {
            supplier.interrupt();
        }
    }

    static public int getInt(String str) {
       return Integer.parseInt(factorySettings.getProperty(str));
    }

    public static void main(String[] args) {
        try {
            factorySettings.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("FactorySettings.properties")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(-1);
        }

        Factory factory = new Factory(getInt("Dealers"), getInt("EngineStorage"), getInt("BodyStorage"), getInt("AccessoryStorage"),
                getInt("AccessorySuppliers"), getInt("Workers"), getInt("CarStorage"));
        factory.start();

        try {
            factory.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
