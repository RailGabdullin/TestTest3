package com.gabdullin.rail.testtest3.data.di;

import android.content.Context;
import android.content.res.AssetManager;

import com.gabdullin.rail.testtest3.R;
import com.gabdullin.rail.testtest3.data.WinnersAppModel;
import com.gabdullin.rail.testtest3.data.db.CardDao;
import com.gabdullin.rail.testtest3.data.db.WinnersAppDataBase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WinnersAppModelMidule {

    @Provides
    static WinnersAppModel provideWinnersAppModel(Context context, Retrofit retrofit, CardDao cardDao, AssetManager assetManager){
        String API_KEY = context.getResources().getString(R.string.API_KEY);
        return new WinnersAppModel(API_KEY, retrofit, cardDao, assetManager);
    }

    @Provides
    static Retrofit provideRetrofit(OkHttpClient.Builder okHttpClientBuilder, Context context){
        String WINNERSAPP_URL = context.getResources().getString(R.string.WINNWERS_URL);
        return new Retrofit.Builder().
                baseUrl(WINNERSAPP_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClientBuilder.build()).
                build();
    }

    @Provides
    static OkHttpClient.Builder provideOkHttpClientBuilder(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
    }

    @Provides
    static HttpLoggingInterceptor provideInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    static WinnersAppDataBase provideWinnerAppDataBase(Context context){
        WinnersAppDataBase database = Room.databaseBuilder(context.getApplicationContext(), WinnersAppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
        return database;
    }

    @Singleton
    @Provides
    static CardDao provideCardDao(WinnersAppDataBase winnersAppDataBase){
        return winnersAppDataBase.cardDao();
    }
}
