package com.leenz.pnrpu.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leenz.pnrpu.models.pagesmodels.TeacherinfopageModel;
import com.leenz.pnrpu.models.pagesmodels.TeacherpageModel;

public class TeacherinfopageVM extends ViewModel {
    public MutableLiveData<TeacherinfopageModel> teacherinfopageModel = new MutableLiveData<TeacherinfopageModel>();
}
