package training.leetcode.concurrency;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution1226 {
    /*
      Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.

        Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both
        left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it
        is not being used by another philosopher. After an individual philosopher finishes eating, they need to put down both
        forks so that the forks become available to others. A philosopher can take the fork on their right or the one on their
        left as they become available, but cannot start eating before getting both forks.

        Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.

        Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will starve; i.e., each can forever
         continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.

         The philosophers' ids are numbered from 0 to 4 in a clockwise order. Implement the function void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork) where:

            philosopher is the id of the philosopher who wants to eat.
            pickLeftFork and pickRightFork are functions you can call to pick the corresponding forks of that philosopher.
            eat is a function you can call to let the philosopher eat once he has picked both forks.
            putLeftFork and putRightFork are functions you can call to put down the corresponding forks of that philosopher.
            The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).

        Five threads, each representing a philosopher, will simultaneously use one object of your class to simulate the process. The function may be called for the same philosopher more than once, even before the last call ends.



        Example 1:

        Input: n = 1
        Output: [[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
        Explanation:
        n is the number of times each philosopher will call the function.
        The output array describes the calls you made to the functions controlling the forks and the eat function, its format is:
        output[i] = [a, b, c] (three integers)
        - a is the id of a philosopher.
        - b specifies the fork: {1 : left, 2 : right}.
        - c specifies the operation: {1 : pick, 2 : put, 3 : eat}.

        Approach

        The solution involves assigning each fork a lock to ensure that only one philosopher can hold a fork at any time.
        To prevent deadlock, we establish a protocol where philosophers pick up forks in a specific order based on their ID:

         Even-numbered philosophers (0, 2, 4) pick up the left fork first, then the right fork.

         Odd-numbered philosophers (1, 3) pick up the right fork first, then the left fork.

        This approach breaks the circular wait condition, which is necessary for deadlock prevention. The solution uses
         ReentrantLock for each fork, ensuring that fork access is thread-safe. The lockInterruptibly method is used to handle
          interrupts during lock acquisition, ensuring that any acquired forks are released if an interrupt occurs.


       When to Use Interruptible Methods:

        Lock Acquisition: Prefer lockInterruptibly() over lock() when using ReentrantLock to allow interruption during waiting.

        Thread Sleep: Use Thread.sleep() (which is interruptible) rather than busy-waiting.

        I/O Operations: Use interruptible I/O methods (e.g., InterruptibleChannel in NIO) or ensure I/O operations can be interrupted by closing the underlying stream/socket.

        Waiting on Conditions: Use await() on Condition objects (which is interruptible) rather than non-interruptible waits.
     */


    // 0, 2, 4, 1, 3 period
    // 0, 2 are eating (1, 3, 4 are waiting) - 1st pair
    // 4, 1 are eating (0, 2, 3 are waiting) - 2nd pair
    // 3, 0 are eating (2, 4, 1 are waiting) - 3rd pair



    class DiningPhilosophers {
        private final Lock[] locks = new ReentrantLock[5];
        private final Semaphore semaphore = new Semaphore(4);

        public DiningPhilosophers() {
            for (int i = 0; i < 5; i++) {
                locks[i] = new ReentrantLock();
            }
        }

        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int left = philosopher;
            int right = (philosopher + 1) % 5;
            semaphore.acquire();
            locks[left].lock();
            pickLeftFork.run();
            locks[right].lock();
            pickRightFork.run();
            eat.run();
            putRightFork.run();
            locks[right].unlock();
            putLeftFork.run();
            locks[left].unlock();
            semaphore.release();
        }
    }

    class DiningPhilosophers0 {
        private final Random rnd = new Random();
        private final ReentrantLock[] forks = new ReentrantLock[5];

        public DiningPhilosophers0() {
            for (int i = 0; i < 5; i++) {
                forks[i] = new ReentrantLock();
            }
        }

        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {

            ReentrantLock rightFork = forks[philosopher];
            ReentrantLock leftFork = forks[(philosopher + 1) % 5];

            boolean lf, rf;

            do {
                rf = rightFork.tryLock(rnd.nextInt(100) + 50, TimeUnit.MILLISECONDS);
                lf = leftFork.tryLock(rnd.nextInt(100) + 50, TimeUnit.MILLISECONDS);
                if (rf && !lf) {
                    rf = false;
                    rightFork.unlock();
                }
            } while (!(lf && rf));

            try {
                pickRightFork.run();
                pickLeftFork.run();
                eat.run();

                putLeftFork.run();
                putRightFork.run();
            } finally {
                rightFork.unlock();
                leftFork.unlock();
            }
        }
    }

    public class DiningPhilosophersWrong {

        private final Lock leftForkLock = new ReentrantLock();
        private final Lock rightForkLock = new ReentrantLock();

        public DiningPhilosophersWrong() {

        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {


            while (true) {
                if (leftForkLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        pickLeftFork.run();

                        if (rightForkLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                            try {
                                pickRightFork.run();
                                eat.run();
                                putRightFork.run();

                                return;
                            } finally {
                                rightForkLock.unlock();
                            }
                        }


                    } finally {
                        putLeftFork.run();
                        leftForkLock.unlock();
                    }
                }
            }
        }
    }

    class DiningPhilosophers3 {

        private final ReentrantLock stateLock;
        private final Condition[] philosopherConditions;
        private int index1 = 0;
        private int index2 = 2;
        private int currentPairFinishedCount = 0;

        public DiningPhilosophers3() {
            stateLock = new ReentrantLock();
            philosopherConditions = new Condition[5];

            for (int i = 0; i < 5; i++) {
                philosopherConditions[i] = stateLock.newCondition();
            }
        }

        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {

            stateLock.lock();
            try {
                //Waiting for our pair
                while (philosopher != index1 && philosopher != index2) {
                    philosopherConditions[philosopher].await();
                }
            } finally {
                stateLock.unlock();
            }

            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();

            stateLock.lock();
            try {
                if (philosopher == index1 || philosopher == index2) {
                    currentPairFinishedCount++;
                    if (currentPairFinishedCount == 2) {
                        index1 = (index1 + 4) % 5;
                        index2 = (index2 + 4) % 5;

                        currentPairFinishedCount = 0;
                        for (int i = 0; i < 5; i++) {
                            philosopherConditions[i].signalAll();
                        }
                    }
                }
            } finally {
                stateLock.unlock();
            }
        }
    }

    class DiningPhilosophers5 {
        private final ReentrantLock[] forkLocks;

        public DiningPhilosophers5() {
            forkLocks = new ReentrantLock[5];

            for (int i = 0; i < 5; i++) {
                forkLocks[i] = new ReentrantLock();
            }
        }

        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int leftForkIndex = philosopher;
            int rightForkIndex = (philosopher + 1) % 5;
            int firstFork = Math.min(leftForkIndex, rightForkIndex);
            int secondFork = Math.max(leftForkIndex, rightForkIndex);

            forkLocks[firstFork].lockInterruptibly();
            try {
                forkLocks[secondFork].lockInterruptibly();
                try {
                    pickLeftFork.run();

                    pickRightFork.run();

                    eat.run();

                    putLeftFork.run();

                    putRightFork.run();

                } finally {
                    forkLocks[secondFork].unlock();
                }
            } finally {
                forkLocks[firstFork].unlock();
            }
        }
    }


}
