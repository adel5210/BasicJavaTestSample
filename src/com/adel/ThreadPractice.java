package com.adel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadPractice {
    public static void main(String[] args) {
        final AtomicInteger inc = new AtomicInteger(0);
        final Thread t1 = new Thread(() -> {
            IntStream.range(0, 5).forEach(i -> {
                Thread.yield();
                System.out.println("Test1 - " + Thread.currentThread().getName());
            });
            System.out.println("Test1 - END - " + Thread.currentThread().getName());
        });
        t1.setPriority(1);
        t1.start();
        IntStream.range(0, 5).forEach(i -> {
            System.out.println("MAIN - " + Thread.currentThread().getName());
        });
        System.out.println("MAIN - END - " + Thread.currentThread().getName());

    }

}
