package com.example.handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.handler.core.Handler;
import com.example.handler.core.Looper;
import com.example.handler.core.Message;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testThreadLocal() {
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "danke";
            }
        };
        threadLocal.set("didi");
        System.out.println("threadLocal = " + threadLocal.get() + " threadName = " + Thread.currentThread().getName()); // threadLocal = danke threadName = main

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("run threadLocal = " + value + " threadName = " + Thread.currentThread().getName()); // threadLocal = danke threadName = Thread-0
                threadLocal.set("outa");
                value = threadLocal.get();
                System.out.println("run threadLocal = " + value + " threadName = " + Thread.currentThread().getName()); // threadLocal = outa threadName = Thread-0

                threadLocal.remove(); // 避免大量无意义的内存占用
                System.out.println("run threadLocal = " + threadLocal.get() + " threadName = " + Thread.currentThread().getName()); // threadLocal = outa threadName = Thread-0

            }
        });
        thread1.start();

    }

    @Test
    public void main() {
        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.obj = "danke hello handler";
                handler.sendMessage(message);
            }
        }).start();

        Looper.loop();
    }
}