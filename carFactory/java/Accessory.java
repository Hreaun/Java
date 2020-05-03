import java.util.UUID;

public class Accessory implements Detail {
    private final UUID accessoryID = UUID.randomUUID();

    @Override
    public String getID() {
        return accessoryID.toString().substring(accessoryID.toString().length() - 12);
    }
}
