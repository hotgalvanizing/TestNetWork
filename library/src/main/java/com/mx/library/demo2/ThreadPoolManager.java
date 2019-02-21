package com.mx.library.demo2;

import android.util.Log;

import com.mx.library.Constants;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    //    只能一端出一端如的单向队列结构
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();


    //    LinkedBlockingDeque就是一个双端队列，任何一端都可以进行元素的出入
    private LinkedBlockingDeque<Runnable> mDeque = new LinkedBlockingDeque<>();


    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mDeque.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //创建延迟队列
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    public void addDelalyTask(HttpTask httpTask) {
        if (httpTask != null) {
            httpTask.setDelaTime(3000);
            mDelayQueue.offer(httpTask);
        }
    }

    public Runnable delayThread = new Runnable() {
        @Override
        public void run() {
            while (true) {
                HttpTask ht = null;

                try {
                    ht = mDelayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ht.getRetryCount() < 3) {
                    mThreadPoolExecutor.execute(ht);
                    ht.setRetryCount(ht.getRetryCount() + 1);
                    Log.d(Constants.TAG,"重试机制");
                }
            }
        }
    };

    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //处理跑出来的任务
                addTask(r);
            }
        });

        mThreadPoolExecutor.execute(communicateThread);
        mThreadPoolExecutor.execute(delayThread);
    }

    //创见交互线程
    public Runnable communicateThread = new Runnable() {
        @Override
        public void run() {
            Runnable runn = null;
            while (true) {
                try {
                    runn = mDeque.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runn);
            }
        }
    };

    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }
}
