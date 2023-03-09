package com.adel;

import java.util.Collections;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorPractice {
    public static void main(String[] args) {
        final AtomicInteger inc = new AtomicInteger(0);
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.rangeClosed(1,10)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(i -> {
            executorService.execute((() -> {
                System.out.println(inc.incrementAndGet()+" -> " +Thread.currentThread().getName());
                Thread.yield();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        });

        executorService.shutdown();
    }
}
