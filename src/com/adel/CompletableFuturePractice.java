package com.adel;

import java.util.concurrent.CompletableFuture;

public class CompletableFuturePractice {

    public static void main(String[] args) {
        final CompletableFuture<Void> process = CompletableFuture.runAsync(() -> {
//            throw new RuntimeException("Breaks");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }).thenApplyAsync(r -> {
            System.out.println("PASS "+Thread.currentThread().getName());
            return r;
        }).exceptionally(e -> {
            System.out.println("FAIL "+Thread.currentThread().getName());
            return null;
        });

    }

}
