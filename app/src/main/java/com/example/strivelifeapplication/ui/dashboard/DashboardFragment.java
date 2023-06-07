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

import com.example.strivelifeapplication.MainActivity;
import com.example.strivelifeapplication.databinding.FragmentDashboardBinding;
import com.example.strivelifeapplication.R;
import java.util.ArrayList;

import org.json.JSONObject;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<Friend> friendList = new ArrayList<>();
    FriendAdapter friendAdapter = null;
    String result;

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
                    String id = editNumber.getText().toString();
                    Friend friend = new Friend("無名"+id, "無");
                    friendList.add(friend);
                    dashboardViewModel.updataFriendList(friendList);
                    //create an ArrayAdaptar from the String Array
                    friendAdapter = new FriendAdapter(requireContext(), R.layout.friend_info, friendList);
                    ListView listView = root.findViewById(R.id.listView2);
                    // Assign adapter to ListView
                    listView.setAdapter(friendAdapter);

                    add_Friend("marow", id);
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

    // function for database

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    public class AddFriendRunnable implements Runnable {
        private String my_name;
        private String friend_name;

        public void setParams(String my_name, String friend_name) {
            this.my_name = my_name;
            this.friend_name = friend_name;
        }

        @Override
        public void run() {
            try {
                URL url = new URL("http://140.116.82.9:9000/addFriend.php");
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                Map<String, Object> FriendData = new HashMap<>();
                FriendData.put("my_name", my_name);
                FriendData.put("friend_name", friend_name);

                JSONObject response_j = new JSONObject(FriendData);
                Log.v("提交數據", response_j.toString());
                connection.connect(); // 開始連線
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter objout = new OutputStreamWriter(os, "UTF-8");
                objout.write(response_j.toString());
                objout.flush();
                os.close();
                objout.close();
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream =
                            connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String box = ""; // 宣告存放用字串
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                        // 每當讀取出一列，就加到存放字串後面
                    }
                    inputStream.close(); // 關閉輸入串流
                    result = box; // 把存放用字串放到全域變數
                    Log.d("HTTP_output", "output!");
                    Log.d("SUCCESS result", "" +": "+result);
                } else {
                    // 处理连接错误
                    // 例如，您可以获取错误消息
                    InputStream errorStream = connection.getErrorStream();
                    String errorMessage = readStream(errorStream); // 将错误流转换为字符串
                    Log.d("BAD ", errorMessage);
                    // 处理错误流
                }

            } catch(Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
                Log.d("BAD result", "" +": "+result);
            }
        }
    }
    private String add_Friend(String my_name, String friend_name){
        AddFriendRunnable addFriend = new AddFriendRunnable();
        addFriend.setParams(my_name, friend_name);
        Thread thread = new Thread(addFriend);
        thread.start();
        return result;
    };

}