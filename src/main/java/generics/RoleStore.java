package generics;

public class RoleStore implements Store<Role> {

    private final Store<Role> storeRol = new MemStore<>();

    @Override
    public void add(Role model) {
        storeRol.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return storeRol.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return storeRol.delete(id);
    }

    @Override
    public Role findById(String id) {
        return storeRol.findById(id);
    }
}
