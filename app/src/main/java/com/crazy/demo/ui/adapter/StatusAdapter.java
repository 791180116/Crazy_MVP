package com.crazy.demo.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.crazy.base.BaseAdapter;
import com.crazy.demo.app.AppAdapter;
import com.crazy.demo.R;

/**
 *    author : NG_crazy
 *
 *    time   : 2019/09/22
 *    desc   : 状态数据列表
 */
public final class StatusAdapter extends AppAdapter<String> {

    public StatusAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends BaseAdapter.ViewHolder {

        private final TextView mTextView;

        private ViewHolder() {
            super(R.layout.status_item);
            mTextView = (TextView) findViewById(R.id.tv_status_text);
        }

        @Override
        public void onBindView(int position) {
            mTextView.setText(getItem(position));
        }
    }
}