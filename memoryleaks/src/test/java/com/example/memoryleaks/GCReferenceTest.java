package com.example.memoryleaks;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GCReferenceTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGCReference() {
        _2MB_Data d1 = new _2MB_Data();
        _2MB_Data d2 = new _2MB_Data();
        d1.instance = d2;
        d2.instance = d1;
        d1 = null;
        d2 = null;
        System.gc();
    }

    class _2MB_Data {
        public Object instance = null;
        private byte[] data = new byte[2 * 1024 * 1024]; // 用来占内存， 测试垃圾回收
    }
}