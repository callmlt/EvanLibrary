package com.evan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evan.R
import com.evan.databinding.BottomDialogFragmentTestBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragmentTest : BottomSheetDialogFragment() {
    var bottomDialogFragmentTest: BottomDialogFragmentTestBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.bottom_dialog_fragment_test, container, false);
        bottomDialogFragmentTest = BottomDialogFragmentTestBinding.bind(view)
        return view;
    }
}
