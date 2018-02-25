package com.koupner.koupner.restaurantlist;

import android.arch.lifecycle.ViewModel;

/**
 * Created by vikasmahato on 08/02/18.
 */

public class RestaurantListViewModel extends ViewModel {
    private boolean mIsSigningIn;
    private Filters mFilters;

    public RestaurantListViewModel() {
        mIsSigningIn = false;
        mFilters = Filters.getDefault();
    }

    public boolean getIsSigningIn() {
        return mIsSigningIn;
    }

    public void setIsSigningIn(boolean mIsSigningIn) {
        this.mIsSigningIn = mIsSigningIn;
    }

    public Filters getFilters() {
        return mFilters;
    }

    public void setFilters(Filters mFilters) {
        this.mFilters = mFilters;
    }
}
