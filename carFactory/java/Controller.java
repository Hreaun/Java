import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class Controller implements Observer {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    final Storage<Car> carStorage;
    final Storage<Body> bodyStorage;
    final Storage<Engine> engineStorage;
    final Storage<Accessory> accessoryStorage;
    ThreadPoolExecutor workers;

    public void printInfo() {
        log.info("Cars have been made: " + workers.getCompletedTaskCount());
        log.info("Cars waiting to be made " + workers.getQueue().size());
    }


    public Controller(ThreadPoolExecutor workers, Storage<Car> carStorage, Storage<Engine> engineStorage,
                      Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage) {
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.workers = workers;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null)
            printInfo();
        while ((carStorage.details.size() + workers.getActiveCount() + workers.getQueue().size()) < carStorage.capacity * 0.2) {
            workers.execute(new Worker(carStorage, engineStorage, bodyStorage, accessoryStorage));
        }
    }
}
