import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Supplier<T> extends Thread{
    private final int supplierWaitTime = Integer.parseInt(Factory.factorySettings.getProperty("supplierWaitTime"));
    private final Storage <T> storage;
    T detail;

    private Constructor<? extends T> ctor;

    public Supplier(Storage<T> storage, Class<? extends T> impl) {
        this.storage = storage;
        try {
            this.ctor = impl.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void makeDetail()  {
        try {
            detail = ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            this.makeDetail();
            try {
                storage.add(detail);
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(supplierWaitTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
