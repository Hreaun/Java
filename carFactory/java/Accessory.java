import java.util.UUID;

public class Accessory implements  Detail {
    private final UUID accessoryID = UUID.randomUUID();

    @Override
    public UUID getID() {
        return accessoryID;
    }
}
