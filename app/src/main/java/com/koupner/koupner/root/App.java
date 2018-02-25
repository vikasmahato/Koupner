package com.koupner.koupner.root;

import android.app.Application;

import com.koupner.koupner.home.HomeModule;
import com.koupner.koupner.restaurantmenu.MenuModule;


/**
 * Created by vikasmahato on 07/02/18.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .homeModule(new HomeModule())
                .menuModule(new MenuModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
