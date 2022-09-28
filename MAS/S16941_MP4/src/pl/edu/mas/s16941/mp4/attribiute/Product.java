package pl.edu.mas.s16941.mp4.attribiute;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Product {
    private String name;
    public static final double minimalProfit = 0.1;
    private double buyingPrice; // atrrtibiute
    private double sellingPrice; // atrrtibiute

    public Product(String name, double buyingPrice, double sellingPrice) throws ModelValidationException {
        setName(name);
        initSetBuyingPrice(buyingPrice);
        setSellingPrice(sellingPrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelValidationException {
        if (name == null || name.isEmpty()) throw new ModelValidationException("Name of product cannot be null");
        this.name = name;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    private void initSetBuyingPrice(double buyingPrice) throws ModelValidationException {
        if (buyingPrice < 0) throw new ModelValidationException("Buying price cannot be lower than 0");
        this.buyingPrice = buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) throws ModelValidationException {
        if (buyingPrice < 0) throw new ModelValidationException("Buying price cannot be lower than 0");
        if (!isProfitOK(buyingPrice, sellingPrice))
            throw new ModelValidationException("Too high buying price. Current profit is lower than minimum 10%");
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) throws ModelValidationException {
        if (sellingPrice < 0) throw new ModelValidationException("Selling price cannot be lower than 0");
        if (!isProfitOK(buyingPrice, sellingPrice))
            throw new ModelValidationException("Too low selling price. Current profit is lower than minimum 10%");
        this.sellingPrice = sellingPrice;
    }

    private static boolean isProfitOK(double buyingP, double sellingP) {
        return (sellingP / buyingP - 1d) >= minimalProfit;
    }
}
