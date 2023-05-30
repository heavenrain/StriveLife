package com.example.strivelife;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.view.ViewGroup;


public class MainActivity extends Activity {

    // taskAdapter 協助連接後端 Task 資料和前端 Task 列表的介面
    TaskAdapter taskAdapter = null;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    ArrayList<Friend> friendList;
    ArrayList<Task> taskList;
    private ListView settingsListView;
    private boolean isSettingsVisible = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 連結資料庫，載入資料
        //
        taskList = new ArrayList<Task>();
        Task task = new Task("睡覺", "再睡五分鐘",2, false, 0, null);
        taskList.add(task);
        task = new Task("運動", "健身大佬",0, false, 0, null);
        taskList.add(task);
        task = new Task("喝水", "台南缺水",1, false, 2000, null);
        taskList.add(task);

        friendList = new ArrayList<Friend>();

        // 顯示 Task 列表, 呈現功能
        displayTaskListView(taskList);

        settingsListView = findViewById(R.id.settingsListView);
        ImageButton settingsButton = findViewById(R.id.settings_button);

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

        // 前往 Friend 列表介面的 Button
        Button btn_to_2 = (Button) findViewById(R.id.button2);
        btn_to_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                intent.putParcelableArrayListExtra("friendList", friendList);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.SECOND_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            friendList = data.getParcelableArrayListExtra("friendList");

            // 在MainActivity中再次启动SecondActivity时，将friendlist传递给SecondActivity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putParcelableArrayListExtra("friendList", friendList);
        }
    }

    // === 顯示 Task 列表, 呈現功能 ===
    // input: 所有 Task 資料, 資料型態ArrayList<Task>
    // output: null
    // 替 ListView(前端介面) 配置 Adapter, 處理關於 Task 列表的所有操作
    // =============================
    private void displayTaskListView(ArrayList<Task> taskList) {

        // 替 ListView(前端介面) 配置 Adapter
        taskAdapter = new TaskAdapter(this, taskList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(taskAdapter);

    }
    private void showSettings() {
        settingsListView.setVisibility(View.VISIBLE);
        isSettingsVisible = true;

        ArrayList<String> settingsData = new ArrayList<String>();
        settingsData.add("更改姓名");
        settingsData.add("更換稱號");
        settingsData.add("更換頭像");


        // 創建您的設定列表適配器，例如 ArrayAdapter 或自定義的適配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, settingsData);

        // 設定設定列表的適配器
        settingsListView.setAdapter(adapter);
    }

    private void hideSettings() {
        settingsListView.setVisibility(View.GONE);
        isSettingsVisible = false;
    }
    // 取得最寬的項目寬度

}
