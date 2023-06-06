package com.example.strivelifeapplication.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.strivelifeapplication.databinding.FragmentDashboardBinding;
import com.example.strivelifeapplication.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<Friend> friendList = new ArrayList<>();
    FriendAdapter friendAdapter = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);


        /////////////////////////////

        friendList = dashboardViewModel.getFriendList();

        friendAdapter = new FriendAdapter(requireContext(), R.layout.friend_info, friendList);
        ListView listView = root.findViewById(R.id.listView2);
        listView.setAdapter(friendAdapter);

        // 加好友按鈕的功能
        ImageButton add_friend_button = root.findViewById(R.id.add_friend_button);
        add_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 按下加好友的按鈕後，跳出輸入"好友 id 視窗"
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                v = getLayoutInflater().inflate(R.layout.add_friend_window,null);
                alertDialog.setView(v);

                // 設置"輸入好友 id 視窗"上的功能
                // 包含"確定"、"取消"、"輸入 id 窗口"
                EditText editNumber = v.findViewById(R.id.editNumber);
                alertDialog.setPositiveButton("確定", ((dialog, which) -> {}));
                alertDialog.setNeutralButton("取消",((dialog, which) -> {}));

                // 顯示"輸入好友 id 視窗"
                AlertDialog dialog = alertDialog.create();
                dialog.show();

                // 實作"輸入好友 id 視窗"上的功能
                // "確定": 新增好友至 Friend 列表
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener((c -> {
                    int id = Integer.parseInt(editNumber.getText().toString());
                    Friend friend = new Friend(id,"無名"+Integer.toString(id));
                    dashboardViewModel.addFriend(friend);
                    //create an ArrayAdaptar from the String Array
                    friendAdapter = new FriendAdapter(requireContext(), R.layout.friend_info, friendList);
                    ListView listView = root.findViewById(R.id.listView2);
                    // Assign adapter to ListView
                    listView.setAdapter(friendAdapter);
                    dialog.dismiss();
                }));
                // "取消: 離開並返回 Friend 介面
                dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener((c -> {
                    dialog.dismiss();
                }));

                //禁用返回跟點擊灰色區域返回
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);

            }
        });

        ////////////////////////////////////////

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}