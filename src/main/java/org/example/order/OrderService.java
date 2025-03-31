package org.example.order;

public class OrderService implements Runnable{
    private int pendingOrders = 0;

    public boolean registerOrder() {
        pendingOrders +=1;
        sleep(500);
        pendingOrders -=1;
        return Math.random() < 0.5;
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.dumpStack();
        }
    }

    @Override
    public void run() {
        while(true) {
            sleep(2000);
            System.out.println("PENDING ORDERS: " + pendingOrders);
        }
    }
}
