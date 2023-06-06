package com.example.strivelifeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        // 设置返回按钮的点击事件
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
