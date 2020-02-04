package com.gabdullin.rail.testtest3;

import android.app.Application;

public class App extends Application {

    private static AppComponents component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponents.builder()
                .context(this)
                .assetManager(getAssets())
                .build();
    }

    public static AppComponents getComponents(){
        return component;
    }
}
