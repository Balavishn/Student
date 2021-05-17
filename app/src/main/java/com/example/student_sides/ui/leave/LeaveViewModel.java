package com.example.student_sides.ui.leave;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeaveViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<CharSequence> text=new MutableLiveData<>();

    public void setText(CharSequence text) {
        this.text.setValue(text);
    }
    public LiveData<CharSequence> getText(){
        return text;
    }
}