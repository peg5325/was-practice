package org.example.counter;

public class Counter implements Runnable{
    private int count = 0;

    public void increment() {
        count ++;
    }

    public void decrement() {
        count --;
    }

    public int getValue() {
        return count;
    }
    @Override
    public void run() {
        // 동기화로 해결
        synchronized (this) {
            this.increment();
            System.out.println("value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue()); // 1
            this.decrement();
            System.out.println("value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue()); // 0
        }
    }
}
