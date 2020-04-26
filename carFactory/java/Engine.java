import java.util.UUID;

public class Engine implements Detail{
    private final UUID engineID = UUID.randomUUID();

    @Override
    public UUID getID() {
        return engineID;
    }
}
