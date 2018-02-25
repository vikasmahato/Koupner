package com.koupner.koupner.pojo;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class Product {

    private String productName;
    private String productDesc;
    private int productPrice;
    private String productImage;
    private String productType;
    private int featured;
    private int inStock;
    private float discountPercentage;

    public Product(String productName, String productDesc, int productPrice, String productImage, String productType)
    {
        this.productName  = productName;
        this.productDesc  = productDesc;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productType = productType;
        this.featured = 0;
        this.inStock = 1;
        this.discountPercentage = 0;

    }

    public Product() {
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getFeatured() {
        return featured;
    }

    public int getInStock() {
        return inStock;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }
}
