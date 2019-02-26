package com.spike.bluetoothtest;

public class learnThread1 {
    static boolean isWaiting = true;
    synchronized static void get() throws InterruptedException {
        if(isWaiting) {
            System.out.println("Waiting");
            learnThread1.class.wait();
            System.out.println("Get and ready");
        } else {
            System.out.println("Get");
        }
    }
    synchronized  static void set() throws InterruptedException {
        if(isWaiting) {
            System.out.println("Prepare");
            Thread.sleep(100);
            learnThread1.class.notify();
            System.out.println("Ready and Notify");
        } else {
            System.out.println("Ready");
        }
    }
    public static void main (String args[]) {
        new Task1().start();
        new Task2().start();
    }
    static class Task1 extends Thread {
        @Override
        public void run() {
            try {
                get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class Task2 extends Thread {
        @Override
        public void run() {
            try {
                set();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
