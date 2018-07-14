package com.stonesmiller.web.core.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/29 19:25
 * Description:
 */
public class DeadLock {

    final int i;

    static DeadLock ref;

    public DeadLock() {
        this.i = 1;
        ref = this;
    }

    private static final Object lock1 = new Object();

    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("Thread-1 lock1");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread-1 lock2");
                }
            }
        }, "Thread-1");
        thread1.setDaemon(true);
        thread1.start();
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("Thread-2 lock2");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread-2 lock1");
                }
            }
        }, "Thread-2");
        thread2.setDaemon(true);
        thread2.start();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName() + "-" + threadInfo.getThreadState() + "-" + threadInfo.isInNative());
        }
        System.out.println(Runtime.getRuntime().availableProcessors());

    }


}
