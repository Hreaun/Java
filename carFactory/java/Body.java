import java.util.UUID;

public class Body implements Detail {
    private final UUID bodyID = UUID.randomUUID();

    @Override
    public String getID() {
        return bodyID.toString().substring(bodyID.toString().length() - 12);
    }
}
