import java.util.UUID;

public class Car {
    private final UUID carID = UUID.randomUUID();
    private Body body;
    private Engine engine;
    private Accessory accessory;

    public Car(Body body, Engine engine, Accessory accessory) {
        this.accessory = accessory;
        this.body = body;
        this.engine = engine;
    }

    public String getInfo (){
        String info = "(Body : " + body.getID() + ", Engine " + engine.getID() + ", Accessory " + accessory.getID() + ")";
        return info;
    }

    public UUID getID() {
        return carID;
    }

}
