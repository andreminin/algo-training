package training.leetcode.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution1114 {
    /*
      Suppose we have a class:

        public class Foo {
          public void first() { print("first"); }
          public void second() { print("second"); }
          public void third() { print("third"); }
        }

        The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().

        Note:

        We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seem to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.
     */

    static class Foo {
        private final Object lock = new Object();
        private volatile String state = "first";

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock) {
                while (!"first".equals(state)) {
                    lock.wait();
                }
            }

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            synchronized (lock) {
                state = "second";
                lock.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (!"second".equals(state)) {
                    lock.wait();
                }
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();

            synchronized (lock) {
                state = "third";
                lock.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (!"third".equals(state)) {
                    lock.wait();
                }
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        Thread[] threads = new Thread[]{
                new Thread(() -> {
                    try {
                        foo.second(() -> System.out.println("second"));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }), new Thread(() -> {
                    try {
                        foo.first(() -> System.out.println("first"));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }), new Thread(() -> {
                    try {
                        foo.third(() -> System.out.println("third"));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                })};

        for(Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Interrupting threads");
        for(Thread thread : threads) {
            if(thread.isAlive()) {
                thread.interrupt();
            }
        }
    }

    static class Foo2 {

        Lock reentrantLock;
        boolean isFirstExecuted;
        boolean isSecondExecuted;
        Condition isFirstExecutionCompleted;
        Condition isSecondExecutionCompleted;

        public Foo2() {
            reentrantLock = new ReentrantLock();
            isFirstExecutionCompleted = reentrantLock.newCondition();
            isSecondExecutionCompleted = reentrantLock.newCondition();
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            reentrantLock.lock();
            try {
                printFirst.run();
                isFirstExecuted = true;
                isFirstExecutionCompleted.signal();
            } finally {
                reentrantLock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {

            reentrantLock.lock();
            try {
                while (!isFirstExecuted)
                    isFirstExecutionCompleted.await();
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                isSecondExecuted = true;
                isSecondExecutionCompleted.signal();
            } finally {
                reentrantLock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            reentrantLock.lock();

            try {
                while (!isSecondExecuted)
                    isSecondExecutionCompleted.await();
                printThird.run();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    class Foo3 {

        private final Semaphore s1;
        private final Semaphore s2;
        private final Semaphore s3;

        public Foo3() {
            s1= new Semaphore(1);
            s2= new Semaphore(0);
            s3= new Semaphore(0);
        }



        public void first(Runnable printFirst) throws InterruptedException {
            s1.acquire();
            printFirst.run();
            s2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s2.acquire();
            printSecond.run();
            s3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            s3.acquire();
            printThird.run();
            s1.release();
        }
    }

    class Foo4 {
        private final CountDownLatch latch1 = new CountDownLatch(1);
        private final CountDownLatch latch2 = new CountDownLatch(1);

        public Foo4() {
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            latch1.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            latch1.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            latch2.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            latch2.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
