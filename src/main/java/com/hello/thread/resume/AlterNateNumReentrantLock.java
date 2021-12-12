package com.hello.thread.resume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlterNateNumReentrantLock {
    private static Lock lock = new ReentrantLock();
    private Condition odd = lock.newCondition();
    private Condition even = lock.newCondition();

    public void printOddNum(Integer val){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" "+val);
            even.signal();
            odd.await();
        }catch (InterruptedException e){
            lock.unlock();
        }
    }

    public void printEvenNum(Integer val){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" "+val);
            odd.signal();
            even.await();
        }catch (InterruptedException e){
            lock.unlock();
        }
    }

    public static void main(String... args) {
        AlterNateNumReentrantLock obj = new AlterNateNumReentrantLock();
        new Thread(new OddThread(obj)).start();
        new Thread(new EvenThread(obj)).start();
    }

}

class OddThread implements Runnable{
    private AlterNateNumReentrantLock obj;
    public OddThread(AlterNateNumReentrantLock obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i= 1; 1 < 10; i+=2) {
            this.obj.printOddNum(i);
        }
    }
}

class EvenThread implements Runnable{
    private AlterNateNumReentrantLock obj;
    public EvenThread(AlterNateNumReentrantLock obj){
        this.obj = obj;
    }
    @Override
    public void run() {
        for (int i= 2; 1 < 10; i+=2) {
            this.obj.printEvenNum(i);
        }
    }
}
