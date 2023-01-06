package com.adel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hashmap not thread-safe");
        final Map<String, Integer> map = new HashMap<>();
        final List<Integer> sumList = parallelSumOf100(map, 100);
        System.out.println(sumList.stream().distinct().count());
        System.out.println(sumList.stream().filter(n->n!=100).count());

        System.out.println("Hashmap thread-safe");
        final Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        final List<Integer> sumList2 = parallelSumOf100(concurrentHashMap, 100);
        System.out.println(sumList2.stream().distinct().count());
        System.out.println(sumList2.stream().filter(n->n!=100).count());

    }

    private static List<Integer> parallelSumOf100(
            final Map<String, Integer> map,
            final Integer executionTimes
    ) throws InterruptedException {
        final List<Integer> sumList= new ArrayList<>(1000);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            final ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++) {
                        map.computeIfPresent("test",(key,val) -> val+1);
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
        }
        return sumList;
    }
}
