package com.chz.thread.resume.alternate.print;

/**
 * @author zhaoc
 * 交替打印abc
 */
public class AlternatePrintABCWay1 {
    private static Object object = new Object();
    private static int state = 0;

    public static void main(String[] args) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (object) {
                        while (state % 3 != 0) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        state++;
                        System.out.println("A"+state);
                        object.notifyAll();
                        System.out.println("A");
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (object) {
                        while (state % 3 != 1) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        state++;
                        System.out.println("B"+state);
                        object.notifyAll();
                        System.out.println("B");
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (object) {
                        while (state % 3 != 2) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        state++;
                        System.out.println("C"+state);
                        object.notifyAll();
                        System.out.println("C");
                    }

                }
            }
        }).start();
    }


}
