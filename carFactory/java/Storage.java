import java.util.LinkedList;
import java.util.Observable;

public class Storage<T> extends Observable {
    public final int capacity;
    public final LinkedList<T> details = new LinkedList<>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(T detail) throws InterruptedException {
        while (true) {
            if (details.size() < capacity) {
                details.add(detail);
                notify();
                setChanged();
                notifyObservers();
                return;
            }
            wait();
        }
    }

    public synchronized T get() throws InterruptedException {
        setChanged();
        notifyObservers();
        while (true) {
            if (details.size() > 0) {
                T detail = details.pop();
                notify();
                setChanged();
                notifyObservers(this);
                return detail;
            }
            wait();
        }
    }

}
