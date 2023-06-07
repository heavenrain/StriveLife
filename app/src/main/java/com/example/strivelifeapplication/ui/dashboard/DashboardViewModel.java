package com.example.strivelifeapplication.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private ArrayList<Friend> friendList = new ArrayList<>();
    String myName;


    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("好友列表");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }

    public ArrayList<Friend> getFriendList() {
        return friendList;
    }

    public void updataFriendList(ArrayList<Friend> friendlist) {
        this.friendList = friendlist;
    }
}