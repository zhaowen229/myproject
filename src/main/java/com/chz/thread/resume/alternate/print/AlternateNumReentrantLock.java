package com.chz.thread.resume.alternate.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoc
 */
public class AlternateNumReentrantLock {
    private final Lock lock = new ReentrantLock();
    private final Condition odd = lock.newCondition();
    private final Condition even = lock.newCondition();

    public void printOddNum(Integer val) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " " + val);
            even.signal();
            odd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printEvenNum(Integer val) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " " + val);
            odd.signal();
            even.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String... args) {
        AlternateNumReentrantLock obj = new AlternateNumReentrantLock();
        Thread oddThread = new Thread(new OddThread(obj));
        Thread evenThread = new Thread(new EvenThread(obj));

        oddThread.start();
        evenThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class OddThread implements Runnable {
    private final AlternateNumReentrantLock obj;

    public OddThread(AlternateNumReentrantLock obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            this.obj.printOddNum(i);
        }
    }
}

class EvenThread implements Runnable {
    private final AlternateNumReentrantLock obj;

    public EvenThread(AlternateNumReentrantLock obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            this.obj.printEvenNum(i);
        }
    }
}
