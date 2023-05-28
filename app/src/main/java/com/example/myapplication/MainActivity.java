package com.example.myapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Window;
import android.text.Spanned;
import java.util.regex.Pattern;
import android.text.InputFilter;


public class MainActivity extends Activity {

    // taskAdapter 協助連接後端 Task 資料和前端 Task 列表的介面
    TaskAdapter taskAdapter = null;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 1;
    ArrayList<Friend> friendList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 連結資料庫，載入資料
        //
        //
        friendList = new ArrayList<Friend>();

        // 顯示 Task 列表, 呈現功能
        displayTaskListView();

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
    private void displayTaskListView() {

        // Array list of countries
        ArrayList<Task> taskList = new ArrayList<Task>();
        Task task = new Task("睡覺", "再睡五分鐘",2, false, 0, null);
        taskList.add(task);
        task = new Task("運動", "健身大佬",0, false, 0, null);
        taskList.add(task);
        task = new Task("喝水", "台南缺水",1, false, 2000, null);
        taskList.add(task);



        // 替 ListView(前端介面) 配置 Adapter
        taskAdapter = new TaskAdapter(this, taskList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(taskAdapter);

    }
}
