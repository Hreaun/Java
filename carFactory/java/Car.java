import java.util.ArrayList;
import java.util.UUID;

public class Car {
    private final UUID carID = UUID.randomUUID();
    private Body body;
    private Engine engine;
    private ArrayList<Accessory> accessories;

    public Car(Body body, Engine engine, ArrayList<Accessory> accessories) {
        this.accessories = accessories;
        this.body = body;
        this.engine = engine;
    }

    public UUID getID() {
        return carID;
    }

    public void printInfo() {
        //print
    }

}
