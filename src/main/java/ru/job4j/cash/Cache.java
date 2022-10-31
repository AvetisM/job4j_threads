package ru.job4j.cash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> getNewModel = (id, inModel) -> {
            Base stored = memory.get(id);
            if (stored.getVersion() != inModel.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            return new Base(id, inModel.getVersion() + 1, inModel.getName());
        };
        return memory.computeIfPresent(model.getId(), getNewModel) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
