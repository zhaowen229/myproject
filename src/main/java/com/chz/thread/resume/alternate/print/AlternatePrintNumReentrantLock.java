package com.chz.thread.resume.alternate.print;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrintNumReentrantLock {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private final static Logger log = LogManager.getLogger(AlternatePrintNumReentrantLock.class);

    private static void alternatePrintNum(int value) {
        try {
            lock.lock();
            condition.signal();
            log.info(Thread.currentThread().getName() + "print num:" + value);
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String... args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i += 2) {
                    alternatePrintNum(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 2; i <= 10; i += 2) {
                    alternatePrintNum(i);
                }
            }
        }).start();

    }


}
