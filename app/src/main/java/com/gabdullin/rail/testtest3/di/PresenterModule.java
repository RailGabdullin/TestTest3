package com.gabdullin.rail.testtest3.di;

import com.gabdullin.rail.testtest3.Presenter;
import com.gabdullin.rail.testtest3.data.WinnersAppModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    static Presenter providePresenter(WinnersAppModel winnersAppModel){
        return new Presenter(winnersAppModel);
    }
}
