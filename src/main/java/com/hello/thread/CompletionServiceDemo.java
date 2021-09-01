package com.hello.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://github.com/lazasha111211/completionservice-demo
 */
public class CompletionServiceDemo {
    static ExecutorService executorService = Executors.newCachedThreadPool();
    static CompletionService completionService = new ExecutorCompletionService<Integer>(executorService);

    public static void main(String[] args){
        completionService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(5000);
                return new Random();
            }
        });

        completionService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                return new Integer("100");
            }
        });

        try {
            System.out.println(completionService.take().get());
            System.out.println(completionService.take().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
