import java.util.ArrayList;

public class Controller extends Thread {
    final Storage<Car> carStorage;
    ArrayList<Worker> workers;
    public final Object request = new Object();
    public Integer carsAmount = 0;


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
