package com.gabdullin.rail.testtest3;

import com.gabdullin.rail.testtest3.data.WinnersAppModel;
import com.gabdullin.rail.testtest3.ui.BaseViewShower;

public class Presenter {

    private WinnersAppModel winnersAppModel;
    private BaseViewShower view;

    public Presenter(WinnersAppModel winnersAppModel) {
        this.winnersAppModel = winnersAppModel;
        winnersAppModel.subscribe(this);
    }


    public void onDataLoaded() {
        if(winnersAppModel.getUrl() != null){
            view.showWebView(winnersAppModel.getUrl());
        }
//        view.showWebView("http://google.com");
    }

    public void onLoadingError() {
//        view.onLoadingError();
    }

    public void attachView(BaseViewShower view){
        this.view = view;
        initCardView();
    }

    private void initCardView() {
        if(winnersAppModel.getCardsList() != null){
            view.showCards(winnersAppModel.getCardsList());
        }
    }
}
