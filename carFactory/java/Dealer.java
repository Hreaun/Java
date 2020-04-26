
public class Dealer extends  Thread{
    private final int dealerWaitTime = 1000;
    private Storage <Car> carStorage;

    public Dealer(Storage <Car> carStorage){
        this.carStorage = carStorage;
    }

    public void getCar() throws InterruptedException {
        Car car = carStorage.get();
        car.printInfo();
    }

    @Override
    public void run() {
        try {
            this.getCar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            sleep(dealerWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
