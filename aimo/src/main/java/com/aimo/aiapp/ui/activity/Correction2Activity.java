package com.aimo.aiapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aimo.aiapp.aop.SingleClick;
import com.aimo.aiapp.http.glide.GlideApp;
import com.aimo.app.base.AppActivity;
import com.aimo.app.APP;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.aimo.aiapp.R;
import com.hjq.toast.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.forward.androids.utils.ImageUtils;
import cn.forward.androids.utils.Util;
import cn.hzw.doodle.DoodleBitmap;
import cn.hzw.doodle.DoodleColor;
import cn.hzw.doodle.DoodleOnTouchGestureListener;
import cn.hzw.doodle.DoodleParams;
import cn.hzw.doodle.DoodlePen;
import cn.hzw.doodle.DoodleShape;
import cn.hzw.doodle.DoodleText;
import cn.hzw.doodle.DoodleTouchDetector;
import cn.hzw.doodle.DoodleView;
import cn.hzw.doodle.IDoodleListener;
import cn.hzw.doodle.core.IDoodle;
import cn.hzw.doodle.core.IDoodleColor;
import cn.hzw.doodle.core.IDoodleItemListener;
import cn.hzw.doodle.core.IDoodlePen;
import cn.hzw.doodle.core.IDoodleSelectableItem;
import cn.hzw.doodle.core.IDoodleShape;
import cn.hzw.doodle.core.IDoodleTouchDetector;
import cn.hzw.doodle.dialog.DialogController;

/**
 * 批改页面
 */
public class Correction2Activity extends AppActivity {
    ImageView ivBack;
    TextView tv_cancel;
    TextView fragmentTitle;
    TextView tv_complete;
    ImageView ivDownload;
    View titleLine;
    ViewPager vpImage;
    FrameLayout flDoodleContent;
    LinearLayout ll_bottom_edit;
    FrameLayout fl_bottom_go_correct;
    ImageView ivPoint1;
    ImageView ivPoint2;
    ImageView ivPoint3;
    RadioButton ivAddText;
    ImageView undo;
    RadioGroup rg_edit;
    RadioButton mBtnEditMode;
    TextView tvGoCorrect;
    TextView tvEdit;
    TextView tvRemove;
    TextView tvCommit;
    LinearLayout mSelectedEditContainer;
    //private StudentListBean studentListBean;
    private PagerAdapter pagerAdapter;
    private int selectedPosition = 0;

    private String url1 = "https://tse2-mm.cn.bing.net/th/id/OIP-C.AaD0qlYDX-RTF_frh-ZhIQHaEo?pid=ImgDet&rs=1";
    private String url2 = "https://tse1-mm.cn.bing.net/th/id/R-C.2f2858f7d8b57a848633cc02711a4a4f?rik=LeMvCvdwReQy9g&riu=http%3a%2f%2ftupian.sioe.cn%2fb%2fbing-home-image%2fpic%2f20140729.jpg&ehk=12NQ%2f5iVQ%2ffbeZWHB9ryq2tAyCh9hDZDCkHe7eAqVQw%3d&risl=&pid=ImgRaw&r=0";
    private String url3 = "https://tse2-mm.cn.bing.net/th/id/OIP-C.AaD0qlYDX-RTF_frh-ZhIQHaEo?pid=ImgDet&rs=1";
    private List<String> urls = new ArrayList<>();
    //private List<File> files = new ArrayList<>();
    private Map<Integer, Bitmap> bitmaps = new HashMap<>();
    private List<ImageView> views = new ArrayList<>();
    private Set<Integer> editMark = new HashSet<>();
    private Map<Integer, String> sevePathMap = new HashMap<>();
    private int compressCP = 80;//图片压缩百分比

    @Override
    public void initData(Bundle savedInstanceState) {
        showWriteGrade();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_correction2;
    }

    @Override
    public Object newPresent() {
        return null;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
        setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   switch (v.getId()) {
                                       case R.id.iv_back:
                                           onBackPressed();
                                           break;
                                       case R.id.iv_download:
                                           try {
                                               saveBmp2Gallery(bitmaps.get(selectedPosition),
                                                       "1_" + "1_" + selectedPosition);
                                           } catch (Exception e) {
                                               e.printStackTrace();
                                           }
                                           break;
                                       case R.id.iv_undo:
                                           if (null != mDoodle && mDoodle.getAllItem().size() > 0) mDoodle.undo();
                                           break;
                                       case R.id.tv_complete:
                                           mDoodle.save();
                                           break;
                                       case R.id.tv_cancel:
                                           isEdit(false);
                                           break;
                                       case R.id.tv_go_correct:
                                           //ToastUtils.showShort("开发中！");
                                           try {
                                               initDoodleView(bitmaps.get(selectedPosition),
                                                       "1_2" +  "2_2" + selectedPosition);
                                           } catch (Exception e) {
                                               e.printStackTrace();
                                           }
                /*DoodleParams params = new DoodleParams(); // 涂鸦参数
                //params.mImagePath = imagePath; // the file path of image
                //不要删，启动两次，启动模式android:launchMode="singleTop"修复要启动两次才能加载bitmap
                MyDoodleActivity.startActivityForResult(Correction2Activity.this, params, list.get(selectedPosition), 11000);
                MyDoodleActivity.startActivityForResult(Correction2Activity.this, params, list.get(selectedPosition), 11000);
                */
                                           break;
                                       case R.id.tv_commit:
                                           break;
                                       case R.id.tv_edit:
                                           if (mTouchGestureListener.getSelectedItem() instanceof DoodleText) {
                                               createDoodleText((DoodleText) mTouchGestureListener.getSelectedItem(), -1, -1);
                                           } else if (mTouchGestureListener.getSelectedItem() instanceof DoodleBitmap) {
                                               //createDoodleBitmap((DoodleBitmap) mTouchGestureListener.getSelectedItem(), -1, -1);
                                           }
                                           break;
                                       case R.id.tv_remove:
                                           mDoodle.removeItem(mTouchGestureListener.getSelectedItem());
                                           mTouchGestureListener.setSelectedItem(null);
                                           break;
                                   }
                               }
                           },
                R.id.iv_back, R.id.iv_download, R.id.rb_hand_write, R.id.rb_line, R.id.rb_add_text, R.id.rb_move_edit,
                R.id.tv_complete, R.id.tv_cancel, R.id.iv_undo, R.id.tv_go_correct, R.id.tv_commit, R.id.tv_edit, R.id.tv_remove);
        ivBack = findViewById(R.id.iv_back);
        tv_cancel = findViewById(R.id.tv_cancel);
        fragmentTitle =findViewById(R.id.fragment_title);
        tv_complete =findViewById(R.id.tv_complete);
        ivDownload =findViewById(R.id.iv_download);
        titleLine =findViewById(R.id.title_line);
        vpImage =findViewById(R.id.vp_image);
        flDoodleContent =findViewById(R.id.fl_doodle_content);
        ll_bottom_edit =findViewById(R.id.ll_bottom_edit);
        fl_bottom_go_correct =findViewById(R.id.fl_bottom_go_correct);
        ivPoint1 =findViewById(R.id.iv_point1);
        ivPoint2 =findViewById(R.id.iv_point2);
        ivPoint3 =findViewById(R.id.iv_point3);
        ivAddText =findViewById(R.id.rb_add_text);
        undo =findViewById(R.id.iv_undo);
        rg_edit =findViewById(R.id.rg_edit);
        mBtnEditMode =findViewById(R.id.rb_move_edit);
        tvGoCorrect =findViewById(R.id.tv_go_correct);
        tvEdit =findViewById(R.id.tv_edit);
        tvRemove =findViewById(R.id.tv_remove);
        tvCommit =findViewById(R.id.tv_commit);
        mSelectedEditContainer =findViewById(R.id.ll_edit);

        /*studentListBean = (StudentListBean) getIntent().getSerializableExtra("studentListBean");
        if (studentListBean == null) {
            ToastUtils.showShort("学生信息异常，请联系客服！");
            return;
        }*/
        fragmentTitle.setText("批改");
        if (mDoodleParams == null) {
            try {
                mDoodleParams = getIntent().getExtras().getParcelable(KEY_PARAMS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isEdit(false);
    }

    public void showWriteGrade() {

                urls.clear();
                urls.add(url1);
                urls.add(url2);
                urls.add(url3);


                for (int i = 0; i < urls.size(); i++) {
                    ImageView view = new ImageView(getContext().getApplicationContext());
                    view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    int finalI = i;
                    GlideApp.with(getContext().getApplicationContext()).asBitmap().load(urls.get(i)).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                            view.setImageBitmap(resource);
                            bitmaps.put(finalI, resource);
                        }
                    });
                    views.add(view);
                }

                pagerAdapter = new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return views.size();
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        ImageView v = views.get(position);
                        try {
                            v.setOnClickListener(v1 -> {

                            });
                            v.setImageBitmap(bitmaps.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        container.addView(views.get(position));
                        return views.get(position);
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                        return view == object;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView(views.get(position));
                    }
                };
                vpImage.setAdapter(pagerAdapter);
                vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        selectedPosition = position;
                        setPointStatue(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
    }

    private void setPointStatue(int position) {
        ivPoint1.setImageResource(R.mipmap.icon_point_unselect);
        ivPoint2.setImageResource(R.mipmap.icon_point_unselect);
        ivPoint3.setImageResource(R.mipmap.icon_point_unselect);
        switch (position) {
            case 0:
                ivPoint1.setImageResource(R.mipmap.icon_point_select);
                break;
            case 1:
                ivPoint2.setImageResource(R.mipmap.icon_point_select);
                break;
            case 2:
                ivPoint3.setImageResource(R.mipmap.icon_point_select);
                break;
        }
    }

    @SingleClick
    @Override
    public void onClick(View view) {

    }



    /**
     * @param isEditStatus
     */
    private boolean isEditStatus = false;

    private void isEdit(Boolean isEdit) {
        this.isEditStatus = isEdit;
        if (isEdit) {
            vpImage.setVisibility(View.GONE);
            flDoodleContent.setVisibility(View.VISIBLE);
            ll_bottom_edit.setVisibility(View.VISIBLE);
            tv_cancel.setVisibility(View.VISIBLE);
            tv_complete.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.GONE);
            ivDownload.setVisibility(View.GONE);
            fl_bottom_go_correct.setVisibility(View.GONE);
            fragmentTitle.setText("批改");
        } else {
            if (mDoodleView != null && mDoodleView.isEditMode()) {
                mDoodleView.setEditMode(false);
            }
            vpImage.setVisibility(View.VISIBLE);
            flDoodleContent.setVisibility(View.GONE);
            ll_bottom_edit.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.GONE);
            tv_complete.setVisibility(View.GONE);
            ivBack.setVisibility(View.VISIBLE);
            ivDownload.setVisibility(View.VISIBLE);
            fl_bottom_go_correct.setVisibility(View.VISIBLE);
            fragmentTitle.setText("作业");
        }

    }

    private void setImageShow(Bitmap bitmap, String path) {
        if (!views.isEmpty() && bitmap != null) {
            views.get(selectedPosition).setImageBitmap(bitmap);
            bitmaps.put(selectedPosition, bitmap);
            pagerAdapter.notifyDataSetChanged();
            editMark.add(selectedPosition);
            sevePathMap.put(selectedPosition, path);
        }
        //ToastUtils.showShort("editMark.size:"+editMark.size());
        if (editMark.size() == urls.size()) {
            tvCommit.setVisibility(View.VISIBLE);
        } else {
            tvCommit.setVisibility(View.GONE);
        }
    }


    //----------------------👇涂鸦相关👇-------------------------//
    public static final int RESULT_ERROR = -111; // 出现错误
    public final static int DEFAULT_MOSAIC_SIZE = 20; // 默认马赛克大小
    public final static int DEFAULT_COPY_SIZE = 20; // 默认仿制大小
    public final static int DEFAULT_TEXT_SIZE = 18; // 默认文字大小
    public final static int DEFAULT_BITMAP_SIZE = 80; // 默认贴图大小

    public static final String KEY_IMAGE_PATH = "key_image_path";
    public static final String KEY_PARAMS = "key_doodle_params";

    private IDoodle mDoodle;
    private DoodleView mDoodleView;
    private DoodleParams mDoodleParams;
    private DoodleOnTouchGestureListener mTouchGestureListener;
    private View mBtnHidePanel, mSettingsPanel;
    private Map<IDoodlePen, Float> mPenSizeMap = new HashMap<>(); //保存每个画笔对应的最新大小

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDoodleParams != null) outState.putParcelable(KEY_PARAMS, mDoodleParams);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if (mDoodleParams != null) mDoodleParams = savedInstanceState.getParcelable(KEY_PARAMS);
    }

    /**
     * 初始化DoodleView 涂鸦
     */
    private void initDoodleView(Bitmap bitmap, String saveName) {
        /*if (mDoodleParams == null) {
            mDoodleParams = getIntent().getExtras().getParcelable(KEY_PARAMS);
        }*/

        if (mDoodleParams == null) {
            mDoodleParams = new DoodleParams();
        }

        mDoodle = mDoodleView = new DoodleViewWrapper(this, bitmap, mDoodleParams.mOptimizeDrawing, new IDoodleListener() {
            @Override
            public void onSaved(IDoodle doodle, Bitmap bitmap, Runnable callback) { // 保存图片为jpg格式
                File doodleFile = null;
                File file = null;
                String savePath = mDoodleParams.mSavePath;
                boolean isDir = mDoodleParams.mSavePathIsDir;
                if (TextUtils.isEmpty(savePath)) {
                    File dcimFile = new File(Environment.getExternalStorageDirectory(), "DCIM");
                    doodleFile = new File(dcimFile, "ruid");
                    //　保存的路径
                    file = new File(doodleFile, saveName + ".jpg");
                } else {
                    if (isDir) {
                        doodleFile = new File(savePath);
                        //　保存的路径
                        file = new File(doodleFile, saveName + ".jpg");
                    } else {
                        file = new File(savePath);
                        doodleFile = file.getParentFile();
                    }
                }
                doodleFile.mkdirs();

                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressCP, outputStream);
                    ImageUtils.addImage(getContentResolver(), file.getAbsolutePath());
                    Intent intent = new Intent();
                    intent.putExtra(KEY_IMAGE_PATH, file.getAbsolutePath());
                    setResult(Activity.RESULT_OK, intent);
                    setImageShow(bitmap, file.getPath());
                    //finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    onError(DoodleView.ERROR_SAVE, e.getMessage());
                } finally {
                    Util.closeQuietly(outputStream);
                }
                isEdit(false);
            }

            public void onError(int i, String msg) {
                setResult(RESULT_ERROR);
                //finish();
            }

            @Override
            public void onReady(IDoodle doodle) {
                //mEditSizeSeekBar.setMax(Math.min(mDoodleView.getWidth(), mDoodleView.getHeight()));

                float size = mDoodleParams.mPaintUnitSize > 0 ? mDoodleParams.mPaintUnitSize * mDoodle.getUnitSize() : 0;
                if (size <= 0) {
                    size = mDoodleParams.mPaintPixelSize > 0 ? mDoodleParams.mPaintPixelSize : mDoodle.getSize();
                }
                // 设置初始值
                //mDoodle.setSize(size);
                mDoodle.setSize(4);
                // 选择画笔
                mDoodle.setPen(DoodlePen.BRUSH);
                mDoodle.setShape(DoodleShape.HAND_WRITE);//普通线
                mDoodle.setColor(new DoodleColor(mDoodleParams.mPaintColor));
                /*if (mDoodleParams.mZoomerScale <= 0) {
                    findViewById(R.id.btn_zoomer).setVisibility(View.GONE);
                }*/
                mDoodle.setZoomerScale(mDoodleParams.mZoomerScale);
                mTouchGestureListener.setSupportScaleItem(mDoodleParams.mSupportScaleItem);

                // 每个画笔的初始值
                mPenSizeMap.put(DoodlePen.BRUSH, mDoodle.getSize());
                mPenSizeMap.put(DoodlePen.MOSAIC, DEFAULT_MOSAIC_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.COPY, DEFAULT_COPY_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.ERASER, mDoodle.getSize());
                mPenSizeMap.put(DoodlePen.TEXT, DEFAULT_TEXT_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.BITMAP, DEFAULT_BITMAP_SIZE * mDoodle.getUnitSize());
            }
        }, null);

        mTouchGestureListener = new DoodleOnTouchGestureListener(mDoodleView,
                new DoodleOnTouchGestureListener.ISelectionListener() {
                    // save states before being selected
                    IDoodlePen mLastPen = null;
                    IDoodleColor mLastColor = null;
                    Float mSize = null;

                    IDoodleItemListener mIDoodleItemListener = new IDoodleItemListener() {
                        @Override
                        public void onPropertyChanged(int property) {
                            if (mTouchGestureListener.getSelectedItem() == null) {
                                return;
                            }
                            if (property == IDoodleItemListener.PROPERTY_SCALE) {
                                //mItemScaleTextView.setText((int) (mTouchGestureListener.getSelectedItem().getScale() * 100 + 0.5f) + "%");
                            }
                        }
                    };

                    @Override
                    public void onSelectedItem(IDoodle doodle, IDoodleSelectableItem selectableItem, boolean selected) {
                        if (selected) {
                            if (mLastPen == null) {
                                mLastPen = mDoodle.getPen();
                            }
                            if (mLastColor == null) {
                                mLastColor = mDoodle.getColor();
                            }
                            if (mSize == null) {
                                mSize = mDoodle.getSize();
                            }
                            mDoodleView.setEditMode(true);
                            mBtnEditMode.setChecked(true);
                            mDoodle.setPen(selectableItem.getPen());
                            mDoodle.setColor(selectableItem.getColor());
                            mDoodle.setSize(selectableItem.getSize());
                            //mEditSizeSeekBar.setProgress((int) selectableItem.getSize());
                            mSelectedEditContainer.setVisibility(View.VISIBLE);
                            //mSizeContainer.setVisibility(View.VISIBLE);
                            //mItemScaleTextView.setText((int) (selectableItem.getScale() * 100 + 0.5f) + "%");
                            selectableItem.addItemListener(mIDoodleItemListener);
                        } else {
                            selectableItem.removeItemListener(mIDoodleItemListener);

                            if (mTouchGestureListener.getSelectedItem() == null) { // nothing is selected. 当前没有选中任何一个item
                                if (mLastPen != null) {
                                    mDoodle.setPen(mLastPen);
                                    mLastPen = null;
                                }
                                if (mLastColor != null) {
                                    mDoodle.setColor(mLastColor);
                                    mLastColor = null;
                                }
                                if (mSize != null) {
                                    mDoodle.setSize(mSize);
                                    mSize = null;
                                }
                                mSelectedEditContainer.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCreateSelectableItem(IDoodle doodle, float x, float y) {
                        if (mDoodle.getPen() == DoodlePen.TEXT) {
                            createDoodleText(null, x, y);
                        } else if (mDoodle.getPen() == DoodlePen.BITMAP) {
                            //createDoodleBitmap(null, x, y);
                        }
                    }
                }) {
            @Override
            public void setSupportScaleItem(boolean supportScaleItem) {
                super.setSupportScaleItem(supportScaleItem);
                if (supportScaleItem) {
                    //mItemScaleTextView.setVisibility(View.VISIBLE);
                } else {
                    //mItemScaleTextView.setVisibility(View.GONE);
                }
            }
        };

        IDoodleTouchDetector detector = new DoodleTouchDetector(getApplicationContext(), mTouchGestureListener);
        mDoodleView.setDefaultTouchDetector(detector);

        mDoodle.setIsDrawableOutside(mDoodleParams.mIsDrawableOutside);
        flDoodleContent.removeAllViews();
        flDoodleContent.addView(mDoodleView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDoodle.setDoodleMinScale(mDoodleParams.mMinScale);
        mDoodle.setDoodleMaxScale(mDoodleParams.mMaxScale);
        isEdit(true);
        rg_edit.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_hand_write:
                    if (null != mDoodleView && mDoodleView.isEditMode()) {
                        mDoodleView.setEditMode(false);
                    }
                    if (null != mDoodle) mDoodle.setPen(DoodlePen.BRUSH);
                    if (null != mDoodle) mDoodle.setShape(DoodleShape.HAND_WRITE);
                    break;
                case R.id.rb_line:
                    if (null != mDoodleView && mDoodleView.isEditMode()) {
                        mDoodleView.setEditMode(false);
                    }
                    if (null != mDoodle) mDoodle.setPen(DoodlePen.BRUSH);
                    if (null != mDoodle) mDoodle.setShape(DoodleShape.LINE);
                    break;
                case R.id.rb_add_text:
                    if (null != mDoodleView && mDoodleView.isEditMode()) {
                        mDoodleView.setEditMode(false);
                    }
                    if (null != mDoodle) mDoodle.setPen(DoodlePen.TEXT);
                    break;
                case R.id.rb_move_edit:
                    if (mDoodleView.isEditMode()) return;
                    if (null != mDoodleView) mDoodleView.setEditMode(!mDoodleView.isEditMode());
                    break;
            }
        });
        rg_edit.check(R.id.rb_hand_write);
    }

    /**
     * 包裹DoodleView，监听相应的设置接口，以改变UI状态
     */
    private class DoodleViewWrapper extends DoodleView {

        public DoodleViewWrapper(Context context, Bitmap bitmap, boolean optimizeDrawing, IDoodleListener listener, IDoodleTouchDetector defaultDetector) {
            super(context, bitmap, optimizeDrawing, listener, defaultDetector);
        }

        private Map<IDoodlePen, Integer> mBtnPenIds = new HashMap<>();

        {
            //mBtnPenIds.put(DoodlePen.BRUSH, R.id.btn_pen_hand);
            //mBtnPenIds.put(DoodlePen.MOSAIC, R.id.btn_pen_mosaic);
            //mBtnPenIds.put(DoodlePen.COPY, R.id.btn_pen_copy);
            //mBtnPenIds.put(DoodlePen.ERASER, R.id.btn_pen_eraser);
            mBtnPenIds.put(DoodlePen.TEXT, R.id.rb_add_text);
        }

        @Override
        public void setPen(IDoodlePen pen) {
            IDoodlePen oldPen = getPen();
            super.setPen(pen);

            tvEdit.setVisibility(View.GONE); // edit btn
            if (pen == DoodlePen.BITMAP || pen == DoodlePen.TEXT) {
                tvEdit.setVisibility(View.VISIBLE); // edit btn
                //mShapeContainer.setVisibility(GONE);
                if (pen == DoodlePen.BITMAP) {
                    //mColorContainer.setVisibility(GONE);
                } else {
                    //mColorContainer.setVisibility(VISIBLE);
                }
            } /*else if (pen == DoodlePen.MOSAIC) {
                //mMosaicMenu.setVisibility(VISIBLE);
                //mShapeContainer.setVisibility(VISIBLE);
                //mColorContainer.setVisibility(GONE);
            } else {
                //mShapeContainer.setVisibility(VISIBLE);
                if (pen == DoodlePen.COPY || pen == DoodlePen.ERASER) {
                    //mColorContainer.setVisibility(GONE);
                } else {
                    //mColorContainer.setVisibility(VISIBLE);
                }
            }*/
            //setSingleSelected(mBtnPenIds.values(), mBtnPenIds.get(pen));

            if (mTouchGestureListener.getSelectedItem() == null) {
                mPenSizeMap.put(oldPen, getSize()); // save
                Float size = mPenSizeMap.get(pen); // restore
                if (size != null) {
                    mDoodle.setSize(size);
                }

            } else {

                return;
            }

            if (pen == DoodlePen.BRUSH) {
                /*Drawable colorBg = mBtnColor.getBackground();
                if (colorBg instanceof ColorDrawable) {
                    mDoodle.setColor(new DoodleColor(((ColorDrawable) colorBg).getColor()));
                } else {
                    mDoodle.setColor(new DoodleColor(((BitmapDrawable) colorBg).getBitmap()));
                }*/
                mDoodle.setColor(new DoodleColor(Color.RED));
            } /*else if (pen == DoodlePen.MOSAIC) {
                if (mMosaicLevel <= 0) {
                    mMosaicMenu.findViewById(R.id.btn_mosaic_level2).performClick();
                } else {
                    mDoodle.setColor(DoodlePath.getMosaicColor(mDoodle, mMosaicLevel));
                }
            } else if (pen == DoodlePen.COPY) {

            } else if (pen == DoodlePen.ERASER) {

            }*/ else if (pen == DoodlePen.TEXT) {
                /*Drawable colorBg = mBtnColor.getBackground();
                if (colorBg instanceof ColorDrawable) {
                    mDoodle.setColor(new DoodleColor(((ColorDrawable) colorBg).getColor()));
                } else {
                    mDoodle.setColor(new DoodleColor(((BitmapDrawable) colorBg).getBitmap()));
                }*/
                mDoodle.setColor(new DoodleColor(Color.RED));
            } /*else if (pen == DoodlePen.BITMAP) {
                Drawable colorBg = mBtnColor.getBackground();
                if (colorBg instanceof ColorDrawable) {
                    mDoodle.setColor(new DoodleColor(((ColorDrawable) colorBg).getColor()));
                } else {
                    mDoodle.setColor(new DoodleColor(((BitmapDrawable) colorBg).getBitmap()));
                }
            }*/
        }

        Map<IDoodleShape, Integer> mBtnShapeIds = new HashMap<>();

        {
            mBtnShapeIds.put(DoodleShape.HAND_WRITE, R.id.rb_hand_write);
            //mBtnShapeIds.put(DoodleShape.ARROW, R.id.btn_arrow);
            mBtnShapeIds.put(DoodleShape.LINE, R.id.rb_line);
            //mBtnShapeIds.put(DoodleShape.HOLLOW_CIRCLE, R.id.btn_holl_circle);
            //mBtnShapeIds.put(DoodleShape.FILL_CIRCLE, R.id.btn_fill_circle);
            //mBtnShapeIds.put(DoodleShape.HOLLOW_RECT, R.id.btn_holl_rect);
            //mBtnShapeIds.put(DoodleShape.FILL_RECT, R.id.btn_fill_rect);
        }

        @Override
        public void setShape(IDoodleShape shape) {
            super.setShape(shape);
            setSingleSelected(mBtnShapeIds.values(), mBtnShapeIds.get(shape));
        }

        //TextView mPaintSizeView = (TextView) this.findViewById(R.id.paint_size_text);

        @Override
        public void setSize(float paintSize) {
            super.setSize(paintSize);
            //mEditSizeSeekBar.setProgress((int) paintSize);
            //mPaintSizeView.setText("" + (int) paintSize);

            if (mTouchGestureListener.getSelectedItem() != null) {
                mTouchGestureListener.getSelectedItem().setSize(getSize());
            }
        }

        @Override
        public void setColor(IDoodleColor color) {
            //IDoodlePen pen = getPen();
            super.setColor(color);

            /*DoodleColor doodleColor = null;
            if (color instanceof DoodleColor) {
                doodleColor = (DoodleColor) color;
            }
            if (doodleColor != null
                    && canChangeColor(pen)) {
                if (doodleColor.getType() == DoodleColor.Type.COLOR) {
                    mBtnColor.setBackgroundColor(doodleColor.getColor());
                } else if (doodleColor.getType() == DoodleColor.Type.BITMAP) {
                    mBtnColor.setBackgroundDrawable(new BitmapDrawable(doodleColor.getBitmap()));
                }

                if (mTouchGestureListener.getSelectedItem() != null) {
                    mTouchGestureListener.getSelectedItem().setColor(getColor().copy());
                }
            }

            if (doodleColor != null && pen == DoodlePen.MOSAIC
                    && doodleColor.getLevel() != mMosaicLevel) {
                switch (doodleColor.getLevel()) {
                    case DoodlePath.MOSAIC_LEVEL_1:
                        this.findViewById(R.id.btn_mosaic_level1).performClick();
                        break;
                    case DoodlePath.MOSAIC_LEVEL_2:
                        this.findViewById(R.id.btn_mosaic_level2).performClick();
                        break;
                    case DoodlePath.MOSAIC_LEVEL_3:
                        this.findViewById(R.id.btn_mosaic_level3).performClick();
                        break;
                }
            }*/
        }

        @Override
        public void enableZoomer(boolean enable) {
            super.enableZoomer(enable);
            /*this.findViewById(R.id.btn_zoomer).setSelected(enable);
            if (enable) {
                Toast.makeText(Correction2Activity.this, "x" + mDoodleParams.mZoomerScale, Toast.LENGTH_SHORT).show();
            }*/
        }

        @Override
        public boolean undo() {
            mTouchGestureListener.setSelectedItem(null);
            return super.undo();
        }

        @Override
        public void clear() {
            super.clear();
            mTouchGestureListener.setSelectedItem(null);
        }

        Boolean mLastIsDrawableOutside = null;

        @Override
        public void setEditMode(boolean editMode) {
            if (editMode == isEditMode()) {
                return;
            }

            super.setEditMode(editMode);
            mBtnEditMode.setSelected(editMode);
            if (editMode) {
                //Toast.makeText(Correction2Activity.this, R.string.doodle_edit_mode, Toast.LENGTH_SHORT).show();
                mLastIsDrawableOutside = mDoodle.isDrawableOutside(); // save
                mDoodle.setIsDrawableOutside(true);
                //mPenContainer.setVisibility(GONE);
                //mShapeContainer.setVisibility(GONE);
                //mSizeContainer.setVisibility(GONE);
                //mColorContainer.setVisibility(GONE);
                //mBtnUndo.setVisibility(GONE);
                //mMosaicMenu.setVisibility(GONE);
            } else {
                if (mLastIsDrawableOutside != null) { // restore
                    mDoodle.setIsDrawableOutside(mLastIsDrawableOutside);
                }
                mTouchGestureListener.center(); // center picture
                if (mTouchGestureListener.getSelectedItem() == null) { // restore
                    setPen(getPen());
                }
                mTouchGestureListener.setSelectedItem(null);
                //mPenContainer.setVisibility(VISIBLE);
                //mSizeContainer.setVisibility(VISIBLE);
                //mBtnUndo.setVisibility(VISIBLE);
            }
        }

        private void setSingleSelected(Collection<Integer> ids, int selectedId) {
            for (int id : ids) {
                if (id == selectedId) {
                    Correction2Activity.this.findViewById(id).setSelected(true);
                } else {
                    Correction2Activity.this.findViewById(id).setSelected(false);
                }
            }
        }

        @Override
        public void refresh() {
            super.refresh();
            if (getAllItem().size() > 0) {
                //undo.setEnabled(true);
                //undo.setClickable(true);
                undo.setImageResource(R.mipmap.icon_undo_s);
            } else {
                //undo.setEnabled(false);
                //undo.setClickable(false);
                undo.setImageResource(R.mipmap.icon_undo_n);
            }
        }
    }

    // 添加文字
    private void createDoodleText(final DoodleText doodleText, final float x, final float y) {
        if (isFinishing()) {
            return;
        }

        DialogController.showInputTextDialog(this, doodleText == null ? null : doodleText.getText(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (v.getTag() + "").trim();
                if (TextUtils.isEmpty(text)) {
                    return;
                }
                if (doodleText == null) {
                    IDoodleSelectableItem item = new DoodleText(mDoodle, text, mDoodle.getSize(), mDoodle.getColor().copy(), x, y);
                    mDoodle.addItem(item);
                    mTouchGestureListener.setSelectedItem(item);
                } else {
                    doodleText.setText(text);
                }
                mDoodle.refresh();
            }
        }, null);
        if (doodleText == null) {
            //mSettingsPanel.removeCallbacks(mHideDelayRunnable);
        }
    }
    //----------------------👆涂鸦相关👆-------------------------//


    /**
     * 保存图片到相册
     *
     * @param bmp     获取的bitmap数据
     * @param picName 自定义的图片名
     */
    public static void saveBmp2Gallery(Bitmap bmp, String picName) {
        /*if (SPUtils.getInstance().getBoolean(picName)) {
            ToastUtils.showShort("已经缓存，请勿重复缓存！");
            return;
        }*/

        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;

        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, System.currentTimeMillis() + "_" + picName + ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(APP.getApplication().getContentResolver(), bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        APP.getApplication().getApplicationContext().sendBroadcast(intent);

        ToastUtils.show("图片保存成功");
        //SPUtils.getInstance().put(picName, true);//保存后用来判断是否已经存储
    }

    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDoodleView.isEditMode()) {
                mDoodleView.setEditMode(false);
                mBtnEditMode.setChecked(false);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public void onBackPressed() {
        // super.onBackPressed();//注释掉这行,back键不退出activity
        if (mDoodle == null
                || mDoodle.getAllItem() == null
                || mDoodle.getItemCount() == 0
                || ivBack.isShown()) {
            finish();
            return;
        }
        if (isEditStatus) isEdit(false);
        if (!(DoodleParams.getDialogInterceptor() != null
                && DoodleParams.getDialogInterceptor().onShow(Correction2Activity.this, mDoodle, DoodleParams.DialogType.SAVE))) {
            DialogController.showEnterCancelDialog(Correction2Activity.this, getString(R.string.doodle_saving_picture), null, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDoodle.save();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isEdit(false);
                    //finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            bitmaps.clear();
            views.clear();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
