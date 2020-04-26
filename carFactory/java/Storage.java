import java.util.LinkedList;

public class Storage<T> {
    private final int capacity = 100; //получается из properties
    private LinkedList<T> details = new LinkedList<>();

    public synchronized void add(T detail) throws InterruptedException {
        while (true) {
            if (details.size() < capacity) {
                details.add(detail);
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
