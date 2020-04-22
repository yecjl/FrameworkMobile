package com.example.memoryleaks;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConcurrentHashMapTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testConcurrentHashMap() {
        System.out.println("Before allocate map, free memory is " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2000000000);
        System.out.println("After allocate map, free memory is " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");

        int i = 0;
        try {
            while (i < 1000000) {
                System.out.println("Before put the " + i + " element, free memory is " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
                map.put(String.valueOf(i), String.valueOf(i));
                System.out.println("After put the " + i + " element, free memory is " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("map size is " + map.size());
        }
    }
}