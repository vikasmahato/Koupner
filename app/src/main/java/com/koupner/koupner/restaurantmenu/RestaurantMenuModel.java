package com.koupner.koupner.restaurantmenu;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class RestaurantMenuModel  implements RestaurantMenuMVP.Model{

    private FirebaseFirestore mFirestore;
    private DocumentReference mRestaurantRef;

    @Override
    public Query getMenuQuery(String restaurantId) {
        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the restaurant
        mRestaurantRef = mFirestore.collection("restaurants").document(restaurantId);

        // Get ratings
        Query ratingsQuery = mRestaurantRef
                .collection("menu")
                .limit(50);

        return ratingsQuery;
    }

    @Override
    public ListenerRegistration addSnapShotListener(EventListener<DocumentSnapshot> snapshot) {
        return mRestaurantRef.addSnapshotListener(snapshot);
    }
}
