package com.chz.thread.resume.productor.customer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者交替执行
 * @author zhaoc
 */
public class ProducerConsumerAlternateExecute {

    public static void main(String[] args) {
        ProducerConsumerAlternateExecute bb = new ProducerConsumerAlternateExecute();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("线程：" + Thread.currentThread().getName() + "第 " + i + "调用生产");
                    bb.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("第 " + i + "调用消费");
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("库存满了。。。");
                notFull.await();
                System.out.println("通知消费者。。。");
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
            System.out.println("生产了一个商品,当前库存:" + count);
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("库存不足。。。");
                notEmpty.await();
                System.out.println("通知生产者。。。");
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            System.out.println("消费了一个商品,当前库存:" + count);
            return x;
        } finally {
            lock.unlock();
        }
    }
}