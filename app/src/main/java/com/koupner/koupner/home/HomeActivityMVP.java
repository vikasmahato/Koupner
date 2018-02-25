package com.koupner.koupner.home;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by vikasmahato on 07/02/18.
 */

public interface HomeActivityMVP {

    interface View {
        void onError();
        void onSuccess();
        void showProgressBar();
        void hideProgressBar();
        boolean signin();
        void showLogoutDialog();
    }

    interface Presenter {
        void setView(HomeActivityMVP.View view);
        FirebaseUser getCurrentUser();
    }

    interface Model {
        FirebaseUser getCurrentUser();
    }
}
