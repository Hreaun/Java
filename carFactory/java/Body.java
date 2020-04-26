import java.util.UUID;

public class Body implements Detail{
    private final UUID bodyID = UUID.randomUUID();

    @Override
    public UUID getID() {
        return bodyID;
    }
}
