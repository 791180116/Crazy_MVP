package com.crazy.demo.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.base.BaseAdapter;
import com.crazy.demo.app.AppAdapter;
import com.crazy.demo.http.glide.GlideApp;
import com.crazy.demo.manager.CacheDataManager;
import com.crazy.demo.ui.activity.VideoSelectActivity;
import com.crazy.demo.widget.PlayerView;
import com.crazy.demo.R;

import java.util.List;

/**
 *    author : NG_crazy
 *    time   : 2020/03/01
 *    desc   : 视频选择适配器
 */
public final class VideoSelectAdapter extends AppAdapter<VideoSelectActivity.VideoBean> {

    private final List<VideoSelectActivity.VideoBean> mSelectVideo;

    public VideoSelectAdapter(Context context, List<VideoSelectActivity.VideoBean> images) {
        super(context);
        mSelectVideo = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends BaseAdapter.ViewHolder {

        private final ImageView mImageView;
        private final CheckBox mCheckBox;
        private final TextView mDurationView;
        private final TextView mSizeView;

        private ViewHolder() {
            super(R.layout.video_select_item);
            mImageView = (ImageView) findViewById(R.id.iv_video_select_image);
            mCheckBox = (CheckBox) findViewById(R.id.iv_video_select_check);
            mDurationView = (TextView) findViewById(R.id.tv_video_select_duration);
            mSizeView = (TextView) findViewById(R.id.tv_video_select_size);
        }

        @Override
        public void onBindView(int position) {
            VideoSelectActivity.VideoBean bean = getItem(position);
            GlideApp.with(getContext())
                    .load(bean.getVideoPath())
                    .into(mImageView);

            mCheckBox.setChecked(mSelectVideo.contains(getItem(position)));

            // 获取视频的总时长
            mDurationView.setText(PlayerView.conversionTime((int) bean.getVideoDuration()));

            // 获取视频文件大小
            mSizeView.setText(CacheDataManager.getFormatSize(bean.getVideoSize()));
        }
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }
}