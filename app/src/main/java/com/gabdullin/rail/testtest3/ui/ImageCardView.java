package com.gabdullin.rail.testtest3.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.gabdullin.rail.testtest3.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class ImageCardView extends CardView {

    ImageView imageView;

    public ImageCardView(@NonNull Context context, Drawable drawable) {
        super(context);
        initView(drawable);
    }

    private void initView(Drawable drawable) {
        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if(drawable != null) imageView.setImageDrawable(drawable);

        int r = (int) (Math.random()*200);
        int g = (int) (Math.random()*200);
        int b = (int) (Math.random()*200);
        imageView.setBackgroundColor(Color.rgb(r, g, b));

//        imageView.setImageResource(R.drawable.amazing_photography_1);

        addView(imageView);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ImageCardView.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.image_height));
    }
}
