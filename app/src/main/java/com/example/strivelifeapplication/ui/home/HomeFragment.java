package com.example.strivelifeapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.app.AlertDialog;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.strivelifeapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import com.example.strivelifeapplication.R;
import com.example.strivelifeapplication.ui.dashboard.Friend;
import com.example.strivelifeapplication.ui.dashboard.FriendAdapter;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ArrayList<Task> taskList;
    TaskAdapter taskAdapter = null;
    private ListView settingsListView;
    private boolean isSettingsVisible = false;
    View root;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        settingsListView = root.findViewById(R.id.settingsListView);
        ImageButton settingsButton = root.findViewById(R.id.settings_button);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSettingsVisible) {
                    hideSettings();
                } else {
                    showSettings();
                }
            }
        });

        ListView listView = root.findViewById(R.id.listView1);

        taskList = new ArrayList<>();
        Task task = new Task("睡覺", "再睡五分鐘", 2, false, 0, null);
        taskList.add(task);
        task = new Task("運動", "健身大佬", 0, false, 0, null);
        taskList.add(task);
        task = new Task("喝水", "台南缺水", 1, false, 2000, null);
        taskList.add(task);

        taskAdapter = new TaskAdapter(requireContext(), taskList);
        listView.setAdapter(taskAdapter);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showSettings() {
        settingsListView.setVisibility(View.VISIBLE);
        isSettingsVisible = true;

        ArrayList<String> settingsData = new ArrayList<String>();
        settingsData.add("更改姓名");
        settingsData.add("更換稱號");
        settingsData.add("更換頭像");


        // 創建您的設定列表適配器，例如 ArrayAdapter 或自定義的適配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, settingsData);

        // 設定設定列表的適配器
        settingsListView.setAdapter(adapter);
        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取点击的项的位置position，并根据需要执行相应的事件
                switch (position) {
                    case 0:
                        openChangeNameDialog();
                        break;
                    case 1:
                        // 执行第二个项的事件
                        break;
                    case 2:
                        break;
                }
            }
        });

    }

    private void hideSettings() {
        settingsListView.setVisibility(View.GONE);
        isSettingsVisible = false;
    }

    private void openChangeNameDialog() {
        // 创建并显示更改姓名的对话框
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
        View v = getLayoutInflater().inflate(R.layout.change_name_window,null);
        alertDialog.setView(v);

        // 設置"更改姓名"上的功能
        // 包含"確定"、"取消"、"輸入姓名窗口"
        EditText editName = v.findViewById(R.id.editeMyName);
        alertDialog.setPositiveButton("確定", ((dialog, which) -> {}));
        alertDialog.setNeutralButton("取消",((dialog, which) -> {}));

        // 顯示"輸入好友 id 視窗"
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        // 實作"輸入好友 id 視窗"上的功能
        // "確定": 新增好友至 Friend 列表
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener((c -> {
            String Name = editName.getText().toString();
            HomeViewModel homeViewModel = new ViewModelProvider(requireParentFragment()).get(HomeViewModel.class);
            homeViewModel.setMyName(Name);
            TextView textView_name = root.findViewById(R.id.textView_name);
            textView_name.setText(Name);
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
}