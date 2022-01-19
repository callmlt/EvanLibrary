package com.evan.lib.vm;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

/**
 * ViewModel
 *
 * @author gr 2020-03-31
 */
public class AppViewModel {

    private static ViewModelProvider viewModelProvider ;
    public void init(Application application){
        ViewModelStore viewModelStore = new ViewModelStore();
        ViewModelProvider.Factory factory= ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        viewModelProvider=new ViewModelProvider(viewModelStore,factory);
    }

    public static <T extends ViewModel> T getViewModel(Class<T> viewModelClass){
        if(!IViewModel.class.isAssignableFrom(viewModelClass)){
            throw new IllegalArgumentException("the model class should be subclass of IViewModel");
        }
        return viewModelProvider.get(viewModelClass);
    }

}

