package com.example.strivelifeapplication.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strivelifeapplication.ContestDetailActivity;
import com.example.strivelifeapplication.R;
import com.example.strivelifeapplication.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> dataList;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);


        // 按下新增比賽按鈕
        Button new_game = view.findViewById(R.id.new_game);
        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡執行按鈕被點擊時的操作
                // 可以在這裡處理跳轉頁面、發送請求等等
                showInputDialog();
            }
        });

        // 初始化数据列表 競賽LIST範例(改成從後端讀取)
        // 初始化数据列表
        dataList = generateDataList();

        // 初始化RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_Contest);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//         初始化适配器
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // 处理列表项的点击事件
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && e.getAction() == MotionEvent.ACTION_UP) {
                    int position = rv.getChildAdapterPosition(childView);
                    String selectedItem = dataList.get(position);

                    // 進入DETAIL ACTIVITY
                    Intent intent = new Intent(getActivity(), ContestDetailActivity.class);
                    intent.putExtra("title", selectedItem);
                    startActivity(intent);

                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });


        return view;
    }


    // 顯示新建比賽頁面
    private void showInputDialog() {
        // 创建AlertDialog的Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 创建一个布局视图用于AlertDialog中的内容
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.new_contest, null);
        builder.setView(dialogView);

        // 获取布局视图中的EditText
        EditText contest_name = dialogView.findViewById(R.id.contest_name);
        EditText contest_success = dialogView.findViewById(R.id.contest_success);
        EditText contest_how_much = dialogView.findViewById(R.id.contest_how_much);
        EditText contest_time = dialogView.findViewById(R.id.contest_time);

        // 選擇頭像獎勵按鈕
        Button extraButton = dialogView.findViewById(R.id.button_new_aword);

        // 设置頭像按钮的点击事件
        extraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理頭像按钮的点击事件
                //!@#$
                Toast.makeText(getContext(), "頭像按鈕", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置对话框的积极按钮（例如确定按钮）
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 在这里处理确定按钮的点击事件
                String text1 = contest_name.getText().toString();
                String text2 = contest_success.getText().toString();
                String text3 = contest_how_much.getText().toString();
                String text4 = contest_time.getText().toString();

                // 执行相应的操作，例如将文本字符串显示在TextView上或进行其他处理
                // 要求後端 1建立新競賽 & 2用戶和該競賽的ATTENDANCE


                // 新增比賽到列表中(IF條件為如果建立成功)
                if(true) {
                    dataList.add(text1);
                    adapter.updateData(dataList);
                }
            }
        });

        // 设置对话框的消极按钮（例如取消按钮）
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 在这里处理取消按钮的点击事件
                dialog.dismiss();
            }
        });

        // 创建并显示AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    // 連接後端生成用戶所有比賽数据列表的方法
    @NonNull
    private List<String> generateDataList() {
        List<String> dataList = new ArrayList<>();
        // 添加数据项到列表中
//        JSONArray data= getAttendedContest("sally");//
//        @NonNull
//                @NonNull
        dataList.add("香菇湯意麵");
        dataList.add("滷肉飯");
        dataList.add("餛飩板條");
        dataList.add("榨菜肉絲米粉");
        dataList.add("香菇湯意麵");
        dataList.add("豬肝麵");
        dataList.add("高麗菜水餃");
        return dataList;
    }


}
