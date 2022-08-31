package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Anton", 2, birthday);
        User user2 = new User("Anton", 2, birthday);
        Map<User, Object> map = new HashMap<>(20);
        map.put(user1, new Object());
        map.put(user2, new Object());
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();
        int hash1 = hashcode1 ^ (hashcode1 >>> 20);
        int hash2 = hashcode2 ^ (hashcode2 >>> 20);
        int bucket1 = hash1 & 19;
        int bucket2 = hash2 & 19;
        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет: %s", hashcode1, hash1, bucket1);
        System.out.println();
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет: %s", hashcode2, hash2, bucket2);
    }
}

