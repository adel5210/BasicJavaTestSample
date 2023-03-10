package com.adel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class Cat {
    private AtomicReference<Integer> tailType;

    public Cat(AtomicReference<Integer> tailType) {
        this.tailType = tailType;
    }

    public AtomicReference<Integer> getTailType() {
        return tailType;
    }

    public void setTailType(AtomicReference<Integer> tailType) {
        this.tailType = tailType;
    }
}

public class CatPractice {

    private static AtomicReference<Integer> tailId = new AtomicReference<>(456);



    public static void main(String[] args) {
        final Cat cat1 = new Cat(tailId);
        tailId.set(123);
        final Cat cat2 = new Cat(tailId);

        System.out.println(cat1.getTailType().equals(cat2.getTailType()));
    }


}
