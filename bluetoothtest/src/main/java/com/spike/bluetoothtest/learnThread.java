package com.spike.bluetoothtest;

public class learnThread {

    public static void main(String args[]) {
        new Task().start();
        new Task().start();

    }
    static class Count {
        static int value;
        Object lock = new Object();
        public void add() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println(value);
        }
        public void add1() throws InterruptedException {
            synchronized (lock) {
                int temp = value;
                Thread.sleep(100);
                value = temp + 1;
                System.out.println(value);
            }
        }
        public synchronized  void add2() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println(value);
        }
        public synchronized static void add3() throws InterruptedException {
            int temp = value;
            Thread.sleep(100);
            value = temp + 1;
            System.out.println(value);
        }
        public void add4() throws InterruptedException {
            synchronized (Count.class) {
                int temp = value;
                Thread.sleep(100);
                value = temp + 1;
                System.out.println(value);
            }
        }
    }
    static class Task extends Thread {
        static Count count = new Count();
        @Override
        public void run() {
            try {
                count.add2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
