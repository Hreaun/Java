import java.util.LinkedList;

public class Storage<T> {
    private final int capacity;
    private final LinkedList<T> details = new LinkedList<>();

    public Storage(int capacity){
        this.capacity = capacity;
    }

    public void getInfo(){
        System.out.println(details.size());
    }

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
