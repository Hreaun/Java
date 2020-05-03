import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Factory {

    public Properties factorySettings;

    ArrayList<Dealer> dealers;
    Storage<Car> carStorage;
    Storage<Engine> engineStorage;
    Storage<Body> bodyStorage;
    Storage<Accessory> accessoryStorage;
    Supplier<Engine> engineSupplier;
    Supplier<Body> bodySupplier;
    ArrayList<Supplier<Accessory>> accessorySuppliers;
    ThreadPoolExecutor workers;

    public Factory(int dealers, int engine, int body, int accessory, int accessorySuppliers, int workers,
                   int cars, Properties factorySettings) {
        this.dealers = new ArrayList<>(dealers);
        this.engineStorage = new Storage<>(engine);
        this.bodyStorage = new Storage<>(body);
        this.accessoryStorage = new Storage<>(accessory);
        this.accessorySuppliers = new ArrayList<>(accessorySuppliers);
        this.workers = (ThreadPoolExecutor) Executors.newFixedThreadPool(workers);
        this.carStorage = new Storage<>(cars);
        engineSupplier = new Supplier<>(engineStorage, Engine.class, Integer.parseInt(factorySettings.getProperty("engineSupplierWaitTime")));
        bodySupplier = new Supplier<>(bodyStorage, Body.class, Integer.parseInt(factorySettings.getProperty("bodySupplierWaitTime")));
        this.factorySettings = factorySettings;
        carStorage.addObserver(new Controller(this.workers, carStorage, engineStorage, bodyStorage, accessoryStorage));
    }

    public void start() {
        engineSupplier.start();
        bodySupplier.start();

        int dealerAmount = Integer.parseInt(factorySettings.getProperty("Dealers"));
        for (int i = 0; i < dealerAmount; i++) {
            dealers.add(new Dealer(carStorage, i, 250 * i + Integer.parseInt(factorySettings.getProperty("dealerWaitTime"))));
            dealers.get(i).start();
        }

        int accSuppAmount = Integer.parseInt(factorySettings.getProperty("AccessorySuppliers"));
        for (int i = 0; i < accSuppAmount; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class,
                    Integer.parseInt(factorySettings.getProperty("accessorySupplierWaitTime"))));
            accessorySuppliers.get(i).start();
        }
    }

    public void stop() {
        for (Dealer dealer : dealers) {
            dealer.interrupt();
        }

        workers.shutdownNow();

        engineSupplier.interrupt();
        bodySupplier.interrupt();
        for (Supplier<Accessory> supplier : accessorySuppliers) {
            supplier.interrupt();
        }
    }

}
