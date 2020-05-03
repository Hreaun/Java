import java.util.logging.Logger;

public class Dealer extends Thread {
    private static final Logger log = Logger.getLogger(Dealer.class.getName());
    private final int dealerID;
    private final int dealerWaitTime;
    private final Storage<Car> carStorage;

    public Dealer(Storage<Car> carStorage, int dealerID, int dealerWaitTime) {
        this.dealerID = dealerID;
        this.carStorage = carStorage;
        this.dealerWaitTime = dealerWaitTime;
    }

    private void printInfo(Car car) {
        log.info("Dealer " + dealerID + " : Auto " + car.getID() + car.getInfo() + '\n');
    }

    public void getCar() throws InterruptedException {
        Car car = carStorage.get();
        printInfo(car);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
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
