import Commands.Commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class Factory {
    private static final Map<String, Supplier<? extends Commands>> registeredCommands = new HashMap<>();

    public static void registerCommand(String type, Supplier<? extends Commands> command) {
        if (!registeredCommands.containsKey(type))
            registeredCommands.put(type, command);
    }

    public static Commands getCommand(String type) {
        Supplier<? extends Commands> command = registeredCommands.get(type);
        return (command != null) ? command.get() : null;
    }

}