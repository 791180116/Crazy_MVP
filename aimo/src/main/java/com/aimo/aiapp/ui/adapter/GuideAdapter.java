package com.aimo.aiapp.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.aimo.aiapp.app.AppAdapter;
import com.crazy.base.BaseAdapter;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *    time   : 2020/08/28
 *    desc   : 引导页适配器
 */
public final class GuideAdapter extends AppAdapter<Integer> {

    public GuideAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends BaseAdapter.ViewHolder {

        private final ImageView mImageView;

        private ViewHolder() {
            super(R.layout.guide_item);
            mImageView = (ImageView) getItemView();
        }

        @Override
        public void onBindView(int position) {
            mImageView.setImageResource(getItem(position));
        }
    }
}