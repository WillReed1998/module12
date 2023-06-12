package org.example;

class MessagePrinterRunnable implements Runnable {

    public void run() {
        while (true) {
            System.out.println("5 seconds passed");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}