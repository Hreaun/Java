import java.util.UUID;

public class Car {
    private final UUID carID = UUID.randomUUID();
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;

    public Car(Body body, Engine engine, Accessory accessory) {
        this.accessory = accessory;
        this.body = body;
        this.engine = engine;
    }

    public String getInfo() {
        return " (Body : " + body.getID() + ", Engine : " + engine.getID() + ", Accessory : " + accessory.getID() + ")";
    }

    public String getID() {
        return carID.toString().substring(carID.toString().length() - 12);
    }

}
