import java.util.LinkedList;
import java.util.logging.Logger;

public class Storage<T> {
    private static final Logger log = Logger.getLogger(Storage.class.getName());
    private final int capacity;
    private final LinkedList<T> details = new LinkedList<>();
    private int madeCount = 0;

    public Storage(int capacity){
        this.capacity = capacity;
    }

    public void printInfo(){
        log.info("Cars have been made: " + madeCount);
    }

    public synchronized void add(T detail) throws InterruptedException {
        while (true) {
            if (details.size() < capacity) {
                details.add(detail);
                madeCount++;
                notify();
                return;
            }
            wait();
        }
    }

    public synchronized T get() throws InterruptedException {
        while (true) {
            if (details.size() > 0) {
                T detail = details.pop();
                notify();
                return detail;
            }
            wait();
        }
    }

}
