package com.hello.keyword;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer_ReentrantLock<T> {
    private Logger log = LogManager.getLogger(MyContainer_ReentrantLock.class);
    private static LinkedList list = new LinkedList<>();
    private final int Max = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == Max) {
                producer.await();
            }
            list.add(t);
            count++;
            log.info("生产了一个产品,产品个数:"+count);
            consumer.signalAll();//通知消费者消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void get() {
        try{
            lock.lock();
            while(list.size() == 0){
                consumer.await();
            }
            list.removeFirst();
            count--;
            log.info("消费了一个产品,产品个数:"+count);
            producer.signalAll();//通知生产者生产
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        MyContainer_ReentrantLock obj = new MyContainer_ReentrantLock<Integer>();
        Producer producer = new Producer(obj);
        Consumer consumer = new Consumer(obj);

        for (int i = 0; i < 2; i++) {
            new Thread(producer).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(consumer).start();
        }
    }



}

class Producer extends Thread {
    private MyContainer_ReentrantLock obj;

    public Producer(MyContainer_ReentrantLock myContainerReentrantLock) {
        this.obj = myContainerReentrantLock;
    }

    @Override
    public void run() {
        while (true) {
            this.obj.put(new Random());
        }
    }

}

class Consumer extends Thread {
    private MyContainer_ReentrantLock obj;

    public Consumer(MyContainer_ReentrantLock myContainerReentrantLock) {
        this.obj = myContainerReentrantLock;
    }

    @Override
    public void run() {
        while (true) {
            this.obj.get();
        }
    }

}

