package com.koupner.koupner.root;

import com.koupner.koupner.MainActivity;
import com.koupner.koupner.home.HomeActivity;
import com.koupner.koupner.home.HomeModule;
import com.koupner.koupner.restaurantmenu.MenuModule;
import com.koupner.koupner.restaurantmenu.RestaurantMenuActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vikasmahato on 07/02/18.
 */
@Singleton
@Component(modules = {ApplicationModule.class, HomeModule.class, MenuModule.class})
public interface ApplicationComponent {

    void inject (MainActivity target);
    void inject (HomeActivity target);
    void inject (RestaurantMenuActivity target);
}
