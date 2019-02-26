package com.spike.bluetoothtest;

import java.util.Timer;
import java.util.TimerTask;

public class learnThread2 {
    // two ways to create a thread
    Thread thread1 = new Thread() {
        @Override
        public void run() {

        }
    };
    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });
    // how to use threadLocal
//    public static void main (String args[]) {
//        final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
//            @Override
//            protected Integer initialValue() {
//                return -1;
//            }
//        };
//        System.out.println(Thread.currentThread().getName() + "value:" + threadLocal.get());
//        threadLocal.set(5);
//        System.out.println(Thread.currentThread().getName() + "new value:" + threadLocal.get());
//        threadLocal.remove();
//        System.out.println(Thread.currentThread().getName() + "remove and value:" + threadLocal.get());
//        threadLocal.set(10);
//
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + "value:" + threadLocal.get());
//                threadLocal.set(5);
//                System.out.println(Thread.currentThread().getName() + "new value:" + threadLocal.get());
//                threadLocal.remove();
//                System.out.println(Thread.currentThread().getName() + "remove and value:" + threadLocal.get());
//                threadLocal.set(10);
//            }
//        }.start();
//    }

    // how to use timer
    public static void main(String args[]) {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            long startTime = System.currentTimeMillis();
//            @Override
//            public void run() {
//                System.out.println("Schedule time:" + (scheduledExecutionTime()-startTime));//expected time
//                System.out.println("Run at:" + (System.currentTimeMillis()-startTime));  //exact time
//            }
//        },1000);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },900);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
            long startTime = System.currentTimeMillis();
            @Override
            public void run() {
                System.out.println("Schedule time:" + (scheduledExecutionTime()-startTime));//expected time
                System.out.println("Run at:" + (System.currentTimeMillis()-startTime));  //exact time
            }
        },0,1000);

    }
}
