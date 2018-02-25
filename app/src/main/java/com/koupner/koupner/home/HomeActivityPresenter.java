package com.koupner.koupner.home;

import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by vikasmahato on 07/02/18.
 */

public class HomeActivityPresenter implements  HomeActivityMVP.Presenter {

    @Nullable
    private HomeActivityMVP.View view;
    private HomeActivityMVP.Model model;

    public HomeActivityPresenter(HomeActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(HomeActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        if(view != null){
            return model.getCurrentUser();
        }
        return null;
    }
}
