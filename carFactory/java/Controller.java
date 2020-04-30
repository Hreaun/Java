import java.util.ArrayList;
import java.util.logging.Logger;

public class Controller extends Thread {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    final Storage<Car> carStorage;
    ArrayList<Worker> workers;
    public final Object request = new Object();
    public Integer carsAmount = 0;

    public void printInfo(){
        log.info("Cars waiting to be made " + carsAmount + '\n');
    }


    public Controller(Storage<Car> storage, ArrayList<Worker> workers) {
        carStorage = storage;
        this.workers = workers;
    }

    @Override
    public void run() {
        try {
            synchronized (request) {
                request.wait();
            }
        } catch (InterruptedException e) {
            this.interrupt();
        }
        while (!isInterrupted()) {
            while ((!isInterrupted()) && (carsAmount > 0)) {
                synchronized (Worker.request) {
                    Worker.request.notify();
                }
            }
        }
    }
}
