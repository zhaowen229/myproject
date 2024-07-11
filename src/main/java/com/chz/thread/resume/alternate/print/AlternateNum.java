package com.chz.thread.resume.alternate.print;

public class AlternateNum {
    private static Object lock = new Object();

    private void printOddNumber(Integer num) {

        synchronized (this) {
            try {
                this.notifyAll();
                System.out.println(Thread.currentThread().getName()+" print num:" + num);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String... args) {
        AlternateNum alternateNum = new AlternateNum();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i=i+2) {
                    alternateNum.printOddNumber(i);

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 2; i <= 10; i=i+2) {
                    alternateNum.printOddNumber(i);
                }
            }
        }).start();
    }

}


