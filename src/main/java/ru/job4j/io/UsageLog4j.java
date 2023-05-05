package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i =  33;
        byte b = 1;
        short s = 11;
        long l = 123;
        double d = 1.1d;
        float f = 1.2f;
        boolean bool = true;
        char c = 'c';
        LOG.debug("int : {}, byte : {}, short : {}, long : {}, double : {}, float : {}, boolean : {}, char : {}", i, b, s, l, d, f, bool, c);
    }
}