package com.leenz.pnrpu.viewmodels;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leenz.pnrpu.models.pagesmodels.TimetablepageModel;
import com.leenz.pnrpu.models.pagesmodels.TimetablepageRowModel;

import java.util.ArrayList;

public class TimetablepageVM extends ViewModel {
    public MutableLiveData<TimetablepageModel> timetablepageModel = new MutableLiveData<TimetablepageModel>();
    public Bundle navArguments = null;
    public MutableLiveData<ArrayList<TimetablepageRowModel>> timetablepageList;
}
