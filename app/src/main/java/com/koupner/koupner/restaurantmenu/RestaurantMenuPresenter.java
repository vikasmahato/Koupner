package com.koupner.koupner.restaurantmenu;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.koupner.koupner.pojo.Cart;
import com.koupner.koupner.pojo.Product;

import java.util.ArrayList;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class RestaurantMenuPresenter implements RestaurantMenuMVP.Presenter{

    @Nullable
    private RestaurantMenuMVP.View view;
    private RestaurantMenuMVP.Model model;
    private Cart mCart;

    public RestaurantMenuPresenter(RestaurantMenuMVP.Model model) {
        this.model = model;
        this.mCart = new Cart();
    }

    @Override
    public void setView(RestaurantMenuMVP.View view) {
        this.view = view;
    }

    @Override
    public int addProductToCart(DocumentSnapshot product) {
        if(view != null) {
            //Log.e("Product", product.toString());
            mCart.setProducts(product.toObject(Product.class));
            return mCart.getCartSize();
        }
        return 0;
    }

    @Override
    public int getCartSize() {
        return mCart.getCartSize();
    }

    @Override
    public ArrayList<Product> getCart() {
        return mCart.getCartProducts();
    }

    @Override
    public Query getMenuQuery(String restaurantId) {
        if(view != null)
            return model.getMenuQuery(restaurantId);
        return null;
    }

    @Override
    public ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> snapshot) {
        if(view != null)
            return model.addSnapShotListener(snapshot);
        return null;
    }
}
