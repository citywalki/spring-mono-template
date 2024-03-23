package pro.walkin.framework.api;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseCaseMetas {
    private final ListMultimap<Class<? extends Command>, UseCaseMeta> useCaseMap = ArrayListMultimap.create();

    private final Map<String, Class<? extends Command>> commands = new ConcurrentHashMap<>();

    public void add(UseCaseMeta useCaseMeta) {
        useCaseMap.put(useCaseMeta.getCommand(), useCaseMeta);
    }

    public List<UseCaseMeta> getUseCases(Class<? extends Command> command) {
        return useCaseMap.get(command);
    }

    public Class<?> getCommand(String command) {
        return commands.get(command);
    }

    public void putCommand(Class<? extends Command> command) {
        commands.put(command.getName(), command);
    }

}
