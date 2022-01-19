package com.evan.lib.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.evan.lib.R;
import com.evan.lib.util.InputMethodMemoryUtil;

/**
 * 所有Activity 的基类
 *
 * @author Evan
 * @version 1.0
 * @date 2021-11-05 15:45:00
 */
public abstract class BaseEvanActivity extends AppCompatActivity {

    protected ImageView backImg;
    protected TextView titleTx, saveTx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeView();
        setContentView(layoutRes());
        initViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        try {
            InputMethodMemoryUtil.fixInputMethodManagerLeak(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /**
     * 加载的布局文件
     *
     * @return 布局文件
     */
    protected abstract @LayoutRes
    int layoutRes();


    /**
     * 加载布局文件之前执行
     */
    protected void beforeView() {

    }

    /**
     * 初始化Views
     */
    protected void initViews() {

    }

    /**
     * 加载数据
     */
    protected void initData() {

    }

    protected void initHeadViews(String title, View.OnClickListener onClickListener) {
        titleTx = findViewById(R.id.titleTx);
        if (titleTx == null) {
            return;
        }
        titleTx.setText(title);
        backImg = findViewById(R.id.backImg);
        saveTx = findViewById(R.id.saveTx);
        if (onClickListener != null) {
            backImg.setOnClickListener(onClickListener);
        }
    }

    protected void initHeadViews(String title) {
        initHeadViews(title, v -> finish());
    }

}
