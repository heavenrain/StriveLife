package com.example.strivelifeapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strivelifeapplication.ui.notifications.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContestDetailActivity extends AppCompatActivity {

    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_detail);

        // 获取标题TextView并设置文本内容
        titleTextView = findViewById(R.id.textView_contest_name);
        String title = getIntent().getStringExtra("title");
        titleTextView.setText(title);

        // 声明并初始化RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView_player);

        List<String> dataList = generateDataList();

        // 创建并设置适配器
        MyAdapter adapter = new MyAdapter(dataList); // 假设dataList是您的人名数据列表
        recyclerView.setAdapter(adapter);

        // 设置布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 设置返回按钮的点击事件
        Button backButton = findViewById(R.id.button_new_player);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContestDetailActivity.this);
                builder.setTitle("输入好友名稱1");

                // 创建一个 EditText 作为对话框的内容
                final EditText inputEditText = new EditText(ContestDetailActivity.this);
                builder.setView(inputEditText);

                // 添加确认按钮
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput = inputEditText.getText().toString();
                        // 调用后端 API 建立ATTDENCE


                        // 新增好友到比賽中(IF條件為如果建立成功)
                        if(true) {
                            dataList.add(userInput);
                            adapter.updateData(dataList);
                        }
                    }
                });

                // 添加取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // 取消对话框
                    }
                });

                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }


    // 要求後端生成參與比賽玩家数据列表的方法
    private List<String> generateDataList() {
        List<String> dataList = new ArrayList<>();
        // 添加数据项到列表中
        dataList.add("雷諾哈特");
        dataList.add("萊西哈特");
        dataList.add("萊特哈特");
        dataList.add("梅露");
        dataList.add("赫夫");
        dataList.add("賽壬");
        return dataList;
    }

}
