package com.gabdullin.rail.testtest3.ui;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.gabdullin.rail.testtest3.Presenter;
import com.gabdullin.rail.testtest3.R;
import com.gabdullin.rail.testtest3.data.db.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerFragment extends Fragment implements BaseViewShower {

    private Unbinder unbinder;
    private static Presenter presenter;
    private ImagesCardRecyclerAdapter imagesCardRecyclerAdapter;

    @BindView(R.id.image_recycler)
    RecyclerView imageRecyclerView;

    @BindView(R.id.webview)
    WebView webView;

    public static RecyclerFragment initInstance(Presenter _presenter) {
        presenter = _presenter;
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        imagesCardRecyclerAdapter = new ImagesCardRecyclerAdapter(null);
        imageRecyclerView.setAdapter(imagesCardRecyclerAdapter);

        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void showWebView(String url){
        imageRecyclerView.setVisibility(View.GONE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WinnersWebViewClient());
        webView.loadUrl(url);
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == MotionEvent.ACTION_UP
                    && webView.canGoBack()) {
                webView.goBack();
                return true;
            }

            return false;
        });
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCards(List<Card> cardsList) {
        List<View> imageCardList = generateImageCardViewList(cardsList);
        imagesCardRecyclerAdapter.updateImageCardList(imageCardList);
    }

    private List<View> generateImageCardViewList(List<Card> cardsList) {
        List<View> imageCardList = new ArrayList<>();
        for (Card card: cardsList) {
            try {
                imageCardList.add(new ImageCardView(getContext(), Drawable.createFromStream(getActivity().getAssets().open(card.getFileName()), null)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            Drawable drawable = Drawable.createFromStream(getActivity().getAssets().open("amazing_photography_1.jpg"), null);
//            imageCardList.add(new ImageCardView(getContext(), drawable));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        imageCardList.add(new ImageCardView(getContext(), null));
//        imageCardList.add(new ImageCardView(getContext(), null));
//        imageCardList.add(new ImageCardView(getContext(), null));

        return imageCardList;
    }

}
