package org.example.stocksSimulator;

public class Stock {
    private final String symbol;
    private Float price;

    public Stock(String symbol, Float price) {
        this.symbol = symbol;
        this.price = price;
    }

    public Float setPrice(Float price) {
        if (price <= 0) return this.price;
        this.price = price;
        return this.price;
    }

    public Float getPrice() {
        return this.price;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
