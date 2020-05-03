import java.util.UUID;

public class Engine implements Detail {
    private final UUID engineID = UUID.randomUUID();

    @Override
    public String getID() {
        return engineID.toString().substring(engineID.toString().length() - 12);
    }
}
