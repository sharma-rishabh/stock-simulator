package org.example;

import org.example.stocksSimulator.MockStockUpdaterRunner;
import org.example.stocksSimulator.Stock;

public class Main {
    public static void main(String[] args) {
        MockStockUpdaterRunner mockStockUpdaterRunner = new MockStockUpdaterRunner();
        for (int i = 0; i<= 100_000; i++) {
            mockStockUpdaterRunner.addStock(
                    new Stock("STOCK_"+i, 100.0f)
            );
        }

        Thread thread = new Thread(mockStockUpdaterRunner);
        thread.start();
    }
}
