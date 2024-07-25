package com.chz.thread.resume.alternate.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoc
 * 交替打印奇偶数
 */
public class AlternatePrintWithReentrantLock {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition evenNumCondition = lock.newCondition();
    private final Condition oddNumCondition = lock.newCondition();

    public static void main(String... args) {
        AlternatePrintWithReentrantLock printWithReentrantLock = new AlternatePrintWithReentrantLock();
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 10; i = i + 2) {
                printWithReentrantLock.printOddNum(i);
            }
        });
        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i = i + 2) {
                printWithReentrantLock.printEvenNum(i);
            }
        });

        oddThread.start();
        evenThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void printEvenNum(Integer val) {
        lock.lock();
        try {
            System.out.println("Even Num: " + val);
            oddNumCondition.signal();
            evenNumCondition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printOddNum(Integer val) {
        lock.lock();
        try {
            System.out.println("Odd Num: " + val);
            evenNumCondition.signal();
            oddNumCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
