package org.example.stocksSimulator;

import org.example.observer.Observer;
import org.example.observer.Subject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MockStockUpdaterRunner implements Subject, Runnable{
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final ArrayList<Stock> stocks = new ArrayList<>();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return this.stocks;
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer: this.observers) {
            observer.update();
        }
    }

    @Override
    public void run() {
        while (true) {
            sleep(1000);
            forkJoinPool.invoke(
                    new UpdateStockPrices(this.stocks)
            );
            this.notifyObservers();
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.dumpStack();
        }
    }
}
