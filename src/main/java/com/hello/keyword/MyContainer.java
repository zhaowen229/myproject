package com.hello.keyword;

import java.util.LinkedList;

/**
 * https://www.zhihu.com/question/57794716?sort=created
 * https://blog.csdn.net/qq_31617121/article/details/79130370
 * @param <T>
 */
public class MyContainer<T> {
    private LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;

    private synchronized void put(T t){
        while (list.size() == MAX){//想想为什么用while而不是用if？
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        this.notifyAll();
    }

    private synchronized T get(){
        T t ;
        while(list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        this.notifyAll();
        return t;
    }

    public static void main(String[] args){
        MyContainer<String> obj = new MyContainer<>();
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++)
                    System.out.println(Thread.currentThread().getName()+":"+obj.get());
            }, "c" + i).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    obj.put(Thread.currentThread().getName() + " " + j);
                }
            },"p" +i).start();
        }
    }
}

