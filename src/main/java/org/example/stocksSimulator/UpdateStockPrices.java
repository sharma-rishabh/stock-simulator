package org.example.stocksSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class UpdateStockPrices extends RecursiveAction {
    private List<Stock> stocks;
    public UpdateStockPrices(List<Stock> stocks) {
        this.stocks = stocks;
    }
    private void updateStockPrice(Stock stock, Boolean moveUp) {
        Float currentPrice = stock.getPrice();
        Float newPrice = (float) (moveUp ? currentPrice * 1.05 : currentPrice * 0.95);
        stock.setPrice(newPrice);
    }

    @Override
    protected void compute() {
        if(this.stocks.size() > 5) {
            int middleIndex = (int) this.stocks.size() / 2;
            List<Stock> firstHalf = this.stocks.subList(0, middleIndex);
            List<Stock> secondHalf = this.stocks.subList(middleIndex, this.stocks.size());
            new UpdateStockPrices(firstHalf).fork();
            new UpdateStockPrices(secondHalf).fork();
        } else {
            for (Stock stock: this.stocks) {
                boolean moveUp = Math.random() > 0.5;
                updateStockPrice(stock, moveUp);
            }
        }
    }
}
