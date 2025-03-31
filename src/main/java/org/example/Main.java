package org.example;

import org.example.stocksSimulator.MockStockUpdaterRunner;
import org.example.stocksSimulator.Stock;
import org.example.user.User;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        MockStockUpdaterRunner mockStockUpdaterRunner = new MockStockUpdaterRunner();
        ExecutorService executors = Executors.newFixedThreadPool(12);
        for (int i = 0; i<= 100_000; i++) {
            mockStockUpdaterRunner.addStock(
                    new Stock("STOCK_"+i, 100.0f)
            );
        }

        for(int i = 0; i <10; i++) {
            User user = new User(mockStockUpdaterRunner, i);
            executors.submit(user);
        }

        Thread thread = new Thread(mockStockUpdaterRunner);
        thread.start();
    }
}
