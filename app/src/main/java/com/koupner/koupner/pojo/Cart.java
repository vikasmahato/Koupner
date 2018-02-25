package com.koupner.koupner.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class Cart implements Parcelable {

    private ArrayList<Product> cartProducts = new ArrayList<Product>();

    public Cart(){}

    public Cart(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    protected Cart(Parcel in) {
        this.cartProducts = in.readArrayList(Product.class.getClassLoader());
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public Product getProducts(int pPosition) {

        return cartProducts.get(pPosition);
    }

    public void setProducts(Product Products) {

        cartProducts.add(Products);

    }

    public int getCartSize() {

        return cartProducts.size();

    }

    public boolean checkProductInCart(Product aProduct) {

        return cartProducts.contains(aProduct);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.cartProducts);
    }
}
