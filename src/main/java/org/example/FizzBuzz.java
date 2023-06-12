package org.example;

import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int i;

    public FizzBuzz(int n) {
        this.n = n;
        this.i = 1;
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (i <= n) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
                i++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (i <= n) {
            if (i % 3 != 0 && i % 5 == 0) {
                printBuzz.run();
                i++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (i <= n) {
            if (i % 15 == 0) {
                printFizzBuzz.run();
                i++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                i++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadFizz = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadBuzz = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadFizzBuzz = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadNumber = new Thread(() -> {
            try {
                fizzBuzz.number(number -> System.out.println(number));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadFizz.start();
        threadBuzz.start();
        threadFizzBuzz.start();
        threadNumber.start();

        threadFizz.join();
        threadBuzz.join();
        threadFizzBuzz.join();
        threadNumber.join();
    }
}