package org.example.user;

import org.example.observer.Observer;
import org.example.order.OrderService;
import org.example.stocksSimulator.MockStockUpdaterRunner;
import org.example.stocksSimulator.Stock;
import java.util.ArrayList;


public class User implements Observer, Runnable {
    private final Portfolio portfolio = new Portfolio();
    private final MockStockUpdaterRunner mockStockUpdaterRunner;
    private int id;
    private OrderService orderService;

    public User(MockStockUpdaterRunner mockStockUpdaterRunner, int id, OrderService orderService) {
        this.mockStockUpdaterRunner = mockStockUpdaterRunner;
        this.id = id;
        this.orderService = orderService;
    }


    @Override
    public void update() {
        ArrayList<Stock> newStocks = this.mockStockUpdaterRunner.getStocks();
        if(orderService.registerOrder()){
            portfolio.updateStocks( newStocks);
        }
        System.out.println("User Id " + this.id + "\n" + portfolio);
    }

    @Override
    public void run() {
        this.mockStockUpdaterRunner.registerObserver(this);
    }
}
