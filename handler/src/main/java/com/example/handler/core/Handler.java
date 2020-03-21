package com.example.handler.core;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @ClassName Handler
 * @Description TODO
 * @Author danke
 * @Date 2020/3/21 8:33 PM
 * @Version 1.0
 */
public class Handler {
    final Looper mLooper;
    final MessageQueue mQueue;

    public Handler () {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    public void handleMessage(@NonNull Message msg) {

    }

    public boolean sendMessage(Message message) {
        MessageQueue queue = this.mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, message);
    }

    private boolean enqueueMessage(MessageQueue queue, Message message) {
        message.target = this;
        return queue.enqueueMessage(message);
    }

    public void dispatchMessage(Message message) {
        handleMessage(message);
    }
}
