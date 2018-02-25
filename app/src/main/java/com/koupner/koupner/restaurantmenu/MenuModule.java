package com.koupner.koupner.restaurantmenu;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikasmahato on 08/02/18.
 */
@Module
public class MenuModule {

    @Provides
    public RestaurantMenuMVP.Presenter provideRestaurantMenuPresenter(RestaurantMenuMVP.Model model){
        return new RestaurantMenuPresenter(model);
    }

    @Provides
    public RestaurantMenuMVP.Model provideHomeActivityModel(){
        return  new RestaurantMenuModel();
    }
}
