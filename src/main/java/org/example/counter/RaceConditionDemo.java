package org.example.counter;

public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        /**
         * RaceCondition 이란?
         * 여러 프로세스 혹은 Thread가 동시에 하나의 자원에 접근하기위해 경쟁하는 상태.
         *
         * 서블릿 객체를 싱글톤으로 관리(인스턴스 하나만 생성하여 공유하는 방식)
         * - 상태를 유지(stateful)하게 설계하면 안됨
         * - Thread safety 하지 않음
         */


    }
}
