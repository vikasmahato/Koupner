package com.koupner.koupner.home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vikasmahato on 07/02/18.
 */
@Module
public class HomeModule {

    @Provides
    public HomeActivityMVP.Presenter provideHomeActivityPresenter(HomeActivityMVP.Model model){
        return new HomeActivityPresenter(model);
    }

    @Provides
    public HomeActivityMVP.Model provideHomeActivityModel(){
        return  new HomeActivityModel();
    }

}
