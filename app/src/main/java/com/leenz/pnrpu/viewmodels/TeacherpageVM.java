package com.leenz.pnrpu.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leenz.pnrpu.models.pagesmodels.TeacherpageModel;

public class TeacherpageVM extends ViewModel {
    public MutableLiveData<TeacherpageModel> teacherpageModel = new MutableLiveData<TeacherpageModel>();
}
