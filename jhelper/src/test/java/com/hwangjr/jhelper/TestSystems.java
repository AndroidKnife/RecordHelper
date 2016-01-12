package com.hwangjr.jhelper;

import org.junit.Test;

import java.util.Map;

public class TestSystems {

    @Test
    public void testHome() {
        System.out.println(Systems.getUserHome());
    }

    public static void main(String[] args) {
        Map<String, String> p = System.getenv();
        for (String s : p.keySet()) {
            System.out.println(s + " : " + p.get(s));
        }
    }
}
