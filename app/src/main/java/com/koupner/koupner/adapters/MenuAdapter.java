package com.koupner.koupner.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.koupner.koupner.R;
import com.koupner.koupner.pojo.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikasmahato on 08/02/18.
 */

/**
 * RecyclerView adapter for menu listing of restaurant.
 */
public class MenuAdapter extends FirestoreAdapter< MenuAdapter.ViewHolder> {

    public interface OnProductSelectedListener {
        void onProductSelected(DocumentSnapshot product);
    }

    private OnProductSelectedListener mListener;

    public MenuAdapter(Query query, OnProductSelectedListener listener){
        super(query);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product_image)
        ImageView produceImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.more)
        ImageButton mOverflowMenu;

        @BindView(R.id.price)
        TextView productPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,final OnProductSelectedListener  listener) {

            mOverflowMenu.setVisibility(View.GONE);

            Product product = snapshot.toObject(Product.class);
            Resources resources = itemView.getResources();

            Glide.with(produceImage.getContext())
                    .load(product.getProductImage())
                    .into(produceImage);
            productName.setText(product.getProductName());
            productPrice.setText(product.getProductPrice());

            // Click listener
            produceImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onProductSelected(snapshot);
                    }
                }
            });
        }
    }
}
