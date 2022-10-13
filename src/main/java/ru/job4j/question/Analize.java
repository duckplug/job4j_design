package ru.job4j.question;


import java.util.*;


public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int a = 0;
        int c = 0;
        int d = 0;

        Map<Integer, String> prevMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.getId(), user.getName());
        }
        Map<Integer, String> currMap = new HashMap<>();
        for (User user : current) {
            currMap.put(user.getId(), user.getName());
        }

        for (Integer s :currMap.keySet()) {
            if ((prevMap.containsKey(s) && !Objects.equals(prevMap.get(s), currMap.get(s)))) {
                c += 1;
            }

            if (!prevMap.containsKey(s)) {
                a += 1;
            }
        }
        for (Integer s : prevMap.keySet()) {
            if (!currMap.containsKey(s)) {
                d += 1;
            }
        }
        return new Info(a, c, d);
    }
}
