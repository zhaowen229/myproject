package com.chz.thread.resume.productor.customer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaoc
 */
public class ProducerCustomerWithSynchronized {
    static Object lock = new Object();

    private static final int max = 5;
    private static int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(5);
    // 最大迭代次数
    private static final int MAX_ITERATIONS = 10;
    // 当前迭代次数
    private static int iteration = 0;


    public static void main(String[] args) {

        Thread producer =new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count == max) {
                        try {
                            System.out.println("库存满了,停止生产,当前库存: " + count);
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    iteration++;
                    System.out.println("生产了一个商品,当前库存: " + count);
                    lock.notifyAll();

                    if (iteration >= MAX_ITERATIONS) {
                        break;
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (count == 0) {
                        try {
                            System.out.println("库存不足,等待生产...,当前库存: " + count);
                            iteration++;
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费了一个商品,当前库存: " + count);
                    lock.notifyAll();

                    if (iteration >= MAX_ITERATIONS) {
                        break;
                    }
                }
            }
        });


        producer.start();
        consumer.start();

        System.out.println("程序结束");

    }


}


