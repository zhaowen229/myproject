package com.chz.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class HooKVerify {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }
        new Thread(new MyThread()).start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread.execute();
            }
        }));
    }

    static class MyThread implements Runnable{
        @Override
        public void run() {
            getObj();
        }

        private static void getObj(){
            Integer obj ;
            while ((obj = queue.poll()) != null){
                if ("50".equals(obj.toString())){
                    System.out.println("program will exit");
                    System.exit(0);
                }
                System.out.println(obj.toString());
            }
        }

        public static void execute() {
            System.out.println("execute java hook.....");
            getObj();
        }
    }
}
