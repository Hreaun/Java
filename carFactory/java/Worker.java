public class Worker extends Thread {
    private Body currentBody;
    private Engine currentEngine;
    private Accessory currentAccessory;
    private final Storage<Engine> engineStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    public static final Object request = new Object();

    public Worker(Storage<Car> carStorage, Storage<Engine> engineStorage, Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage) {
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.bodyStorage = bodyStorage;
    }

    public void makeCar(Storage<Car> carStorage) throws InterruptedException {
        Car car = new Car(currentBody, currentEngine, currentAccessory);
        carStorage.add(car);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                synchronized (request) {
                    request.wait();
                }
            } catch (InterruptedException e) {
                break;
            }
            try {
                currentEngine = engineStorage.get();
                currentBody = bodyStorage.get();
                currentAccessory = accessoryStorage.get();
            } catch (InterruptedException e) {
                break;
            }
            try {
                this.makeCar(carStorage);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
