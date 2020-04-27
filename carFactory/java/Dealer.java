import java.sql.Date;
import java.text.SimpleDateFormat;

public class Dealer extends  Thread{
    private final int dealerID;
    private final int dealerWaitTime = Integer.parseInt(Factory.factorySettings.getProperty("dealerWaitTime"));
    private int carsAmount;
    private final Storage <Car> carStorage;

    public Dealer(Storage <Car> carStorage, int carsAmount, int dealerID){
        this.dealerID = dealerID;
        this.carsAmount = carsAmount;
        this.carStorage = carStorage;
    }

    private void printInfo(Car car){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + ": Dealer " + dealerID + ":Auto " + car.getID() + car.getInfo());
    }

    public void getCar() throws InterruptedException {
        Car car = carStorage.get();
        printInfo(car);
        carsAmount--;

    }

    @Override
    public void run() {
        while(carsAmount > 0) {
            try {
                this.getCar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(dealerWaitTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
