package com.example.strivelife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.EditText;


import java.util.ArrayList;

public class SecondActivity extends Activity {
    // friendAdapter 協助連接後端 Friend 資料和前端 Friend 列表的介面
    SecondActivity.FriendAdapter friendAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);

        // 從 MainActivity 獲取 Friend 資料
        //
        //

        // 顯示 Friend 列表, 呈現功能
        // 从Intent中获取friendlist
        ArrayList<Friend> friendList = getIntent().getParcelableArrayListExtra("friendList");

        displayFriendListView(friendList);

        // 前往 To-Do-List 列表介面的 Button
        Button btn_to_1 = (Button) findViewById(R.id.button1);
        btn_to_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("friendList", friendList);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });

    }

    // === 顯示 Friend 列表, 呈現功能 ===
    // input: 所有 Friend 資料, 資料型態 ArrayList<Friend>
    // output: null
    // 替 ListView(前端介面) 配置 Adapter, 處理關於 Friend 列表的所有操作
    // =============================
    private void displayFriendListView(ArrayList<Friend> friendList){

        // 替 ListView(前端介面) 配置 Adapter
        friendAdapter = new SecondActivity.FriendAdapter(SecondActivity.this, R.layout.friend_info, friendList);
        ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(friendAdapter);

        // 加好友按鈕的功能
        ImageButton add_friend_button = (ImageButton) findViewById(R.id.add_friend_button);
        add_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 按下加好友的按鈕後，跳出輸入"好友 id 視窗"
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecondActivity.this);
                v = getLayoutInflater().inflate(R.layout.enter_window,null);
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
                    friendList.add(friend);
                    //create an ArrayAdaptar from the String Array
                    friendAdapter = new SecondActivity.FriendAdapter(SecondActivity.this, R.layout.friend_info, friendList);
                    ListView listView = (ListView) findViewById(R.id.listView2);
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
    }
    private class FriendAdapter extends ArrayAdapter<Friend> {

        private ArrayList<Friend> friendList;

        public FriendAdapter(Context context, int textViewResourceId,
                               ArrayList<Friend> friendList) {
            super(context, textViewResourceId, friendList);
            this.friendList = new ArrayList<Friend>();
            this.friendList.addAll(friendList);
        }

        private class ViewHolder {
            TextView name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SecondActivity.FriendAdapter.ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.friend_info, null);

                holder = new SecondActivity.FriendAdapter.ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            }
            else {
                holder = (SecondActivity.FriendAdapter.ViewHolder) convertView.getTag();
            }
            Friend friend = friendList.get(position);
            holder.name.setText(friend.getName());
            return convertView;

        }

    }

}
