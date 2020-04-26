import java.util.ArrayList;

public class Worker extends Thread {
    private Body currentBody;
    private Engine currentEngine;
    private ArrayList<Accessory> currentAccessories = new ArrayList<>();

    public void getDetail(Storage<Detail> storage) throws InterruptedException {
        if (storage.getClass().getComponentType() == Engine.class)
            currentEngine = (Engine) storage.get();
        if (storage.getClass().getComponentType() == Body.class)
            currentBody = (Body) storage.get();
        if (storage.getClass().getComponentType() == Accessory.class)
            currentAccessories.add((Accessory) storage.get());
    }

    public void makeCar(Storage<Car> carStorage) throws InterruptedException {
        Car car = new Car(currentBody, currentEngine, currentAccessories);
        carStorage.add(car);
    }

    @Override
    public void run() {

    }
}
