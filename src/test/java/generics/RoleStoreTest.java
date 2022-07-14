package generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddNewRoleThenFindIt() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write code"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("Write code"));
    }

    @Test
    public void whenAddRoleThenFindNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("123", "Review"));
        Role rsl = store.findById("1");
        assertNull(rsl);
    }

    @Test
    public void whenAddDuplicateAndFindWrite() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.add(new Role("1", "Review"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("Write"));
    }

    @Test
    public void replaceThenReview() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.replace("1", new Role("1", "Review"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is ("Review"));
    }

    @Test
    public void whenReplaceIsFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.replace("2", new Role("1", "Review"));
        assertFalse(store.replace("2", new Role("1", "Review")));
    }

    @Test
    public void whenReplaceButNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.replace("99", new Role("1", "Review"));
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("Write"));
    }

    @Test
    public void whenDeleteRoleThenIsWrite() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.delete("77");
        Role rsl = store.findById("1");
        assertThat(rsl.getRole(), is("Write"));
    }

    @Test
    public void whenDeleteRoleThenNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Write"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertNull(rsl);
    }


}