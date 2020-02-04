package com.gabdullin.rail.testtest3.ui;

import com.gabdullin.rail.testtest3.data.db.Card;

import java.util.List;

public interface BaseViewShower {

    void showWebView(String url);

    void showCards(List<Card> cardsList);
}
