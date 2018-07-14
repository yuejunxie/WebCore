package com.stonesmiller.web.core.concurrency;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/1 16:54
 * Description:
 */
public class ProducerConsumerMode {

    private static final Object lock = new Object();

    private static volatile int size = 0;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (size >= 10) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size++;
                    System.out.println("PRODUCER:" + size);
                    lock.notifyAll();
                }
            }

        }, "producer");

        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (size <= 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size--;
                    System.out.println("CONSUMER:" + size);
                    lock.notifyAll();
                }
            }
        }, "consumer");

        producer.start();
        consumer.start();
    }

}
