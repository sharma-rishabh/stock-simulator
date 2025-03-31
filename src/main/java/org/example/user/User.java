package org.example.user;

import org.example.observer.Observer;
import org.example.stocksSimulator.MockStockUpdaterRunner;
import org.example.stocksSimulator.Stock;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

public class User implements Observer, Runnable {
    private final Portfolio portfolio = new Portfolio();
    private final MockStockUpdaterRunner mockStockUpdaterRunner;
    private int id;

    public User(MockStockUpdaterRunner mockStockUpdaterRunner, int id) {
        this.mockStockUpdaterRunner = mockStockUpdaterRunner;
        this.id = id;
    }


    @Override
    public void update() {
        System.out.println("Update Received");
        ArrayList<Stock> newStocks = this.mockStockUpdaterRunner.getStocks();
        portfolio.updateStocks( newStocks);
        System.out.println("User Id " + this.id + "\n" + portfolio);
    }

    @Override
    public void run() {
        this.mockStockUpdaterRunner.registerObserver(this);
    }
}
