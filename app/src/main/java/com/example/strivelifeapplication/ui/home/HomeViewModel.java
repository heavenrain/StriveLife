package com.example.strivelifeapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.strivelifeapplication.ui.dashboard.Friend;
import com.example.strivelifeapplication.R;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private ArrayList<Task> taskList = new ArrayList<>();
    String myName;
    private String avatarName = "";


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("To-Do-List");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void updataTaskList(ArrayList<Task> tasklist) {
        this.taskList = tasklist;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }
    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String name) {
        this.avatarName = name;
    }
}