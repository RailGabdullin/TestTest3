package com.gabdullin.rail.testtest3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gabdullin.rail.testtest3.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class ImagesCardRecyclerAdapter extends RecyclerView.Adapter<ImagesCardRecyclerAdapter.ImageCardHolder> {

    private List<View> imageCardViewList;

    public ImagesCardRecyclerAdapter(List<View> imageCardViewList) {
        this.imageCardViewList = imageCardViewList;
    }

    @NonNull
    @Override
    public ImagesCardRecyclerAdapter.ImageCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageCardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesCardRecyclerAdapter.ImageCardHolder holder, int position) {
        holder.bind(imageCardViewList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageCardViewList.size();
    }

    public void updateImageCardList(List<View> imageCardList) {
        imageCardViewList = imageCardList;
    }

    public class ImageCardHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.card_item)
        FrameLayout frameLayout;

        public ImageCardHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(View view){
            frameLayout.addView(view);
        }
    }
}
