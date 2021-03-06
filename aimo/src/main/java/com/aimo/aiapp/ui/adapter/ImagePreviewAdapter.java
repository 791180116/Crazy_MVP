package com.aimo.aiapp.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.aimo.aiapp.http.glide.GlideApp;
import com.aimo.app.base.AppAdapter;
import com.crazy.base.BaseAdapter;
import com.github.chrisbanes.photoview.PhotoView;
import com.aimo.aiapp.R;

/**
 *    author : NG_crazy
 *    time   : 2020/08/28
 *    desc   : 图片预览适配器
 */
public final class ImagePreviewAdapter extends AppAdapter<String> {

    public ImagePreviewAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends BaseAdapter.ViewHolder {

        private final PhotoView mPhotoView;

        private ViewHolder() {
            super(R.layout.image_preview_item);
            mPhotoView = (PhotoView) getItemView();
        }

        @Override
        public void onBindView(int position) {
            GlideApp.with(getContext())
                    .load(getItem(position))
                    .into(mPhotoView);
        }
    }
}