import java.util.ArrayList;

public class Controller extends Thread{
    Storage<Car> carStorage;
    ArrayList<Worker> workers;


    public Controller(Storage<Car> storage, ArrayList<Worker> workers){
        carStorage = storage;
        this.workers = workers;
    }

    @Override
    public void run() {
        /*while (!isInterrupted()) {
            if (carStorage.getSize() < Integer.parseInt(Factory.factorySettings.getProperty("Dealers"))) {
                workers.notifyAll();
            }
        }*/
    }
}
