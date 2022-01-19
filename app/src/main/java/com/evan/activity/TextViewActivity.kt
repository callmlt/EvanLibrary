package com.evan.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evan.databinding.ActivityTextViewBinding

/**
 * @description:
 * @author：GR
 * @date：2022/1/19  16:51
 */
class TextViewActivity :AppCompatActivity() {
    var activityTextViewBinding:ActivityTextViewBinding?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTextViewBinding= ActivityTextViewBinding.inflate(layoutInflater)
        setContentView(activityTextViewBinding!!.root)
    }
}