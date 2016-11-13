package com.v7.alumniassociation.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.v7.alumniassociation.R;
import com.v7.alumniassociation.helper.IntentHelper;
import com.v7.alumniassociation.widget.ExtendedViewPager;
import com.v7.alumniassociation.widget.TouchImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by v7 on 2016/11/11.
 */

public class BrowseImgActivity extends AppCompatActivity {

    @BindView(R.id.titleBack)
    ImageView titleBack;
    @BindView(R.id.titleBackRippleView)
    MaterialRippleLayout titleBackRippleView;
    @BindView(R.id.titleTitle)
    TextView titleTitle;
    @BindView(R.id.titleFunction)
    TextView titleFunction;
    @BindView(R.id.titleFunctionRippleView)
    MaterialRippleLayout titleFunctionRippleView;
//    @BindView(R.barId.viewPager)
//    ExtendedViewPager viewPager;
    @BindView(R.id.image)
    TouchImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browseimg);
        ButterKnife.bind(this);

        titleTitle.setText("图片");
        titleFunctionRippleView.setVisibility(View.GONE);

        String imageUrl = getIntent().getStringExtra(IntentHelper.IMAGE_URL);

//        List<String> images = new ArrayList<>();
//        images.add(imageUrl);
//        TouchImageAdapter adapter = new TouchImageAdapter(images);
//
//        viewPager.setAdapter(adapter);

        Log.e("imageUrl",imageUrl);
        Glide.with(this).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
            }
        });
    }

    @OnClick(R.id.titleBack)
    public void onBackClick(){
        finish();
    }

    class TouchImageAdapter extends PagerAdapter {

        List<String> images;

        public TouchImageAdapter(List<String> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());
            Glide.with(BrowseImgActivity.this).load(images.get(position)).into(img);
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
