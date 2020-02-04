package com.gabdullin.rail.testtest3;

import android.content.Context;
import android.content.res.AssetManager;

import com.gabdullin.rail.testtest3.data.di.WinnersAppModelMidule;
import com.gabdullin.rail.testtest3.di.PresenterModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, WinnersAppModelMidule.class})
public interface AppComponents {
    void injectsMainActivity(MainActivity mainActivity);


    @Component.Builder
    interface Builder{

        AppComponents build();

        @BindsInstance
        Builder context(Context context);

        @BindsInstance
        Builder assetManager(AssetManager assetManager);
    }
}
