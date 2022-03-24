package com.example.batalsqlmidtermproject;

public class Product {
    private String productID;
    private String productName;
    private String productDesc;
    private double productPrice;
    private int productQty;

    public Product(String productID, String productName, String productDesc, double productPrice, int productQty) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
}
