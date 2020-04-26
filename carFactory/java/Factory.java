

public class Factory<T> extends Thread{
    private Storage<T> storage;
    private Class<T> detailCreator;

    public Factory (Storage<T> storage, Class<T> detailCreator){
        this.detailCreator = detailCreator;
        this.storage = storage;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
        }
    }
}
