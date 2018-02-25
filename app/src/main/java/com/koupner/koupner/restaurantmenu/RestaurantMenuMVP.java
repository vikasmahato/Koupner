package com.koupner.koupner.restaurantmenu;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.koupner.koupner.pojo.Product;

import java.util.ArrayList;

/**
 * Created by vikasmahato on 08/02/18.
 */

public interface RestaurantMenuMVP {

    interface View {
        void onError();
        void onSuccess();
        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter {
        void setView(RestaurantMenuMVP.View view);
        int addProductToCart(DocumentSnapshot product);
        int getCartSize();
        ArrayList<Product> getCart();
        Query getMenuQuery(String restaurantId);
        ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> snapshot);
    }

    interface Model {
        Query getMenuQuery(String restaurantId);
        ListenerRegistration addSnapShotListener(EventListener<DocumentSnapshot> snapshot);
    }

}
