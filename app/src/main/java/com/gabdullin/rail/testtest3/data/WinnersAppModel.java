package com.gabdullin.rail.testtest3.data;

import android.app.Application;
import android.content.res.AssetManager;

import com.gabdullin.rail.testtest3.Presenter;
import com.gabdullin.rail.testtest3.data.db.Card;
import com.gabdullin.rail.testtest3.data.db.CardDao;
import com.gabdullin.rail.testtest3.data.model.DataModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WinnersAppModel extends Application {

    private static String API_KEY;
    private static Presenter presenter;
    private static winnersapp winnersapp;
    private static DataModel dataModel;
    private CardDao cardDao;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public WinnersAppModel(String API_KEY, Retrofit retrofit, CardDao cardDao, AssetManager assetManager) {
        this.API_KEY = API_KEY;
        this.cardDao = cardDao;
        initDataBase(assetManager);
        initRetrofit(retrofit);
        requestStartInstance();
    }

    public void subscribe(Presenter presenter) {
        this.presenter = presenter;
    }

    public String getUrl() {
        return dataModel.getUrl();
    }

    private static void requestStartInstance(){
        winnersapp.startRequest(API_KEY)
                .enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        dataModel = response.body();
                        if (dataModel != null) presenter.onDataLoaded();
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        presenter.onLoadingError();
                    }
                });
    }

    private static void initRetrofit(Retrofit retrofit){
        winnersapp = retrofit.create(winnersapp.class);
    }

    public List<Card> getCardsList(){
        List<Card> cardList = cardDao.getAll();
        return cardList;
    }


    private void initDataBase(AssetManager assetManager) {
        cardDao.deleteAll();
        try {
            String list[] = assetManager.list("");
            if (list != null)
                for (int i = 0; i < list.length; ++i) {
                    if(list[i].contains(".")){
                        cardDao.insert(new Card(list[i]));
                    }
                }
        } catch (IOException e) {
        }
    }
}
