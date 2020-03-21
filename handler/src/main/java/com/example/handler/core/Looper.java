package com.example.handler.core;

/**
 * @ClassName Looper
 * @Description TODO
 * @Author danke
 * @Date 2020/3/21 8:27 PM
 * @Version 1.0
 */
public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    final MessageQueue mQueue;

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        Looper looper = myLooper();
        MessageQueue queue = looper.mQueue;
        for(;;) {
            Message message = queue.next();
            if (message != null && message.target != null) {
                message.target.dispatchMessage(message);
            }
        }
    }
}