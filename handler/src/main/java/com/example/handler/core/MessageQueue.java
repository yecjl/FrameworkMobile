package com.example.handler.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName MessageQueue
 * @Description TODO
 * @Author danke
 * @Date 2020/3/21 8:31 PM
 * @Version 1.0
 */
public class MessageQueue {
    private BlockingQueue<Message> queue = new ArrayBlockingQueue<>(50);

    public boolean enqueueMessage(Message message) {
        try {
            queue.put(message);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Message next() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
