import java.sql.Date;
import java.text.SimpleDateFormat;

public class Dealer extends Thread {
    private final int dealerID;
    private final int dealerWaitTime = Integer.parseInt(Factory.factorySettings.getProperty("dealerWaitTime"));
    private int carsAmount;
    private final Storage<Car> carStorage;
    private final Controller controller;

    public Dealer(Storage<Car> carStorage, int carsAmount, int dealerID, Controller controller) {
        this.dealerID = dealerID;
        this.carsAmount = carsAmount;
        this.carStorage = carStorage;
        this.controller = controller;
    }

    private void printInfo(Car car) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + ": Dealer " + dealerID + ":Auto " + car.getID() + car.getInfo());
    }

    public void getCar() throws InterruptedException {
        Car car = carStorage.get();
        printInfo(car);
        carsAmount--;
        if (carsAmount > 0) {
            synchronized (controller.carsAmount) {
                controller.carsAmount++;
                synchronized (controller.request) {
                    controller.request.notify();
                }
            }
        }
    }

    @Override
    public void run() {
        synchronized (controller.carsAmount) {
            controller.carsAmount++;
            synchronized (controller.request) {
                controller.request.notify();
            }
        }
        while (carsAmount > 0) {
            try {
                this.getCar();
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(dealerWaitTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
