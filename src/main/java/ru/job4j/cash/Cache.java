package ru.job4j.cash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = memory.get(model.getId());
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        Base newModel = new Base(model.getId(), model.getVersion() + 1, model.getName());
        return memory.computeIfPresent(model.getId(), (key, value) -> newModel) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
