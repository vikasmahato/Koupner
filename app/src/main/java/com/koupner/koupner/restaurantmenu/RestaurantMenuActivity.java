package com.koupner.koupner.restaurantmenu;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.koupner.koupner.R;
import com.koupner.koupner.adapters.CartAdapter;
import com.koupner.koupner.adapters.MenuAdapter;
import com.koupner.koupner.pojo.Product;
import com.koupner.koupner.root.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantMenuActivity extends AppCompatActivity implements
        EventListener<DocumentSnapshot>,
        RestaurantMenuMVP.View, MenuAdapter.OnProductSelectedListener{

    private static final String TAG = "RestaurantMenu";

    private FirebaseFirestore mFirestore;
    private DocumentReference mRestaurantRef;

    public static final String KEY_RESTAURANT_ID = null;
    private MenuAdapter mProductAdapter;
    private ListenerRegistration mMenuRegistration;



    @Inject
    RestaurantMenuMVP.Presenter presenter;

    @BindView(R.id.product)
    RecyclerView mProductRecycler;

    @BindView(R.id.view_empty_menu)
    ViewGroup mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        // Get restaurant ID from extras
        String restaurantId = getIntent().getExtras().getString(KEY_RESTAURANT_ID);
        if (restaurantId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_RESTAURANT_ID);
        }

        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the restaurant
        mRestaurantRef = mFirestore.collection("restaurants").document(restaurantId);

        // Get ratings
        Query ratingsQuery = mRestaurantRef
                .collection("menu")
                .whereEqualTo("inStock", 1);

        // RecyclerView
        mProductAdapter = new MenuAdapter(ratingsQuery, this) {
            @Override
            protected void onDataChanged() {
                if (getItemCount() == 0) {
                    mProductRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mProductRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }

        };

        mProductRecycler.setLayoutManager(new GridLayoutManager((this),2));
        mProductRecycler.setAdapter(mProductAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_cart:

                final Dialog fullscreenDialog = new Dialog(this, R.style.DialogFullscreen);
                fullscreenDialog.setContentView(R.layout.cart_dialog);


                CartAdapter cartAdapter = new CartAdapter(presenter.getCart());

                RecyclerView rvTest = (RecyclerView) fullscreenDialog.findViewById(R.id.cart_product_list);
                rvTest.setHasFixedSize(true);
                rvTest.setLayoutManager(new LinearLayoutManager(this));
                rvTest.setItemAnimator(new DefaultItemAnimator());
                rvTest.setAdapter(cartAdapter);

                ViewGroup emptyView = (LinearLayout) fullscreenDialog.findViewById(R.id.view_empty_menu);

                Button applyDeals = (Button) fullscreenDialog.findViewById(R.id.apply_deals);

                if(presenter.getCartSize() == 0){
                    rvTest.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    applyDeals.setVisibility(View.GONE);
                }

                ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close);
                img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fullscreenDialog.dismiss();
                    }
                });

                applyDeals.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("APPLYDEALS", "applyy deals clicked");
                    }
                });

                fullscreenDialog.show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        mProductAdapter.startListening();
        mMenuRegistration = mRestaurantRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        mProductAdapter.stopListening();

        if (mMenuRegistration != null) {
            mMenuRegistration.remove();
            mMenuRegistration = null;
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onProductSelected(DocumentSnapshot product) {
        int cartSize = presenter.addProductToCart(product);
        if(cartSize != 0)
            Toast.makeText(this, "Added to cart " + cartSize, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "restaurant:onEvent", e);
            return;
        }

        onMenuLoaded(snapshot.toObject(Product.class));
    }

    private void onMenuLoaded(Product product) {
       // Change Global view of this view
        
    }
}
