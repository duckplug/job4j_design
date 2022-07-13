package generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, storage.get(id), model);
    }


    @Override
    public boolean delete(String id) {
        T del = storage.remove(id);
        return del != null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}