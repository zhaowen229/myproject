package com.chz.thread.resume.alternate.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoc
 * 交替打印abc
 */
public class AlternatePrintABCWay2 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();
    private static int state = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (state %3 != 0) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + "A");
                    state++;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (state %3 != 1) {
                        condition2.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + "B");
                    state++;
                    condition3.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (state %3 != 2) {
                        condition3.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + "C");
                    state++;
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();


    }
}
