package com.evan.lib.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.evan.lib.R;
import com.evan.lib.databinding.DialogEvanLoadBinding;

/**
 * @description:
 * @author：EVAN
 * @date：2022/1/14 11:48
 *  <!--加载框的颜色-->
 *  <color name="loadingColor">#333333</color>
 */
public class EvanLoadDialog extends Dialog {
    private DialogEvanLoadBinding mViewBinding;

    public EvanLoadDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*
        //加背景阴影
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.01f;
        Objects.requireNonNull(getWindow()).setAttributes(lpWindow);*/

        mViewBinding = DialogEvanLoadBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
        setCanceledOnTouchOutside(false);
        //按返回不消除
        //setCancelable(false);
    }

    public DialogEvanLoadBinding getViewBinding() {
        return mViewBinding;
    }
}
