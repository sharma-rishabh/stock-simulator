package org.example.user;

import org.example.stocksSimulator.Stock;

import java.util.ArrayList;
import java.util.Optional;

public class Portfolio {
    private final ArrayList<Stock> holdings = new ArrayList<>();
    private int maxHolding = 10;

    public void updateStocks(ArrayList<Stock> stocks) {
        int space = maxHolding - holdings.size();
        if (space == 0) {
            removeLowPricedStock(1);
            space = 1;
        }
        addStocks(stocks, space);
    }
    public  void addStocks(ArrayList<Stock> stocks, int space) {
        for(int i=0; i < space; i++) {
            int index = (int) (Math.random() * stocks.size());
            this.holdings.add(
                    stocks.get(index)
            );
        }
    }

    public void removeLowPricedStock(int count){
        Optional<Stock> lowestPricedStock = this.holdings.stream().reduce(
                ((stock, stock2) -> stock.getPrice() < stock2.getPrice() ? stock : stock2)
        );
        this.holdings.remove(lowestPricedStock);
    }

    public Optional<Float> currentValue() {
        return this.holdings.stream().map((Stock::getPrice)).reduce(Float::sum);
    }
    @Override
    public String toString() {
        String representation = "-------" + "\n";
        representation = representation + "Current Value: " +  this.currentValue() + " \n";
        representation = representation + "---------";
        return representation;
    }
}
