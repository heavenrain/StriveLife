package com.example.strivelifeapplication.ui.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strivelifeapplication.AddAttendanceManager;
import com.example.strivelifeapplication.AddContestManager;
import com.example.strivelifeapplication.ContestDetailActivity;
import com.example.strivelifeapplication.GetAttendedContestManager;
import com.example.strivelifeapplication.R;
import com.example.strivelifeapplication.UpdateavatarManager;
import com.example.strivelifeapplication.databinding.FragmentNotificationsBinding;
import com.example.strivelifeapplication.ui.home.HomeFragment;
import com.example.strivelifeapplication.ui.home.Task;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Integer> imageList;

    private List<String> dataList;

    private FragmentNotificationsBinding binding;
    private android.app.AlertDialog imagePickerDialog;
    View view;
    String Name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Name = "marow";
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        imageList = new ArrayList<>();
        imageList.add(AvatarNameToId("water"));
        imageList.add(AvatarNameToId("sleep"));
        imageList.add(AvatarNameToId("muscle"));
        imageList.add(AvatarNameToId("hero"));

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
        // 调整间隙
        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new ChallengerDecoration(spacing));

        // 初始化适配器
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
                openChangeAvatarDialog();
                Toast.makeText(getContext(), "頭像按鈕", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置对话框的积极按钮（例如确定按钮）
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 在这里处理确定按钮的点击事件
                String text1 = contest_name.getText().toString();
                String text2 = contest_success.getText().toString();
                String text3 = contest_how_much.getText().toString();
                String text4 = contest_time.getText().toString();

                // 执行相应的操作，例如将文本字符串显示在TextView上或进行其他处理
                // 要求後端 1建立新競賽 & 2用戶和該競賽的ATTENDANCE
                AddContestManager addContestManager = new AddContestManager();
                if (text3.equals("")){
                    addContestManager.add_Contest(text1,0,text4, Integer.parseInt(text3),"隨便",
                            Integer.parseInt(text2),"water");
                    dataList.add(text1);
                    adapter.updateData(dataList);
                }
                else {
                    addContestManager.add_Contest(text1,1,text4, Integer.parseInt(text3),"隨便",
                            Integer.parseInt(text2),"water");
                    dataList.add(text1);
                    adapter.updateData(dataList);
                }
                AddAttendanceManager addAttendanceManager = new AddAttendanceManager();
                addAttendanceManager.add_Attendance(Name, text1, 0);
                Log.d("contest name", text1);
//                !@#$
//                int type = 0, expected_amount = 0;
//                String deadline = "06:00:00";
//                String goal = "";
//                if(!"".equals(text1)){
//                    if (!"".equals(text4)) {deadline = text4;};
//                    if (!"".equals(text2)) {goal = text2;}
//                    if (!"".equals(text3)){
//                        type = 1;
//                        expected_amount = Integer.parseInt(text3);
//                    }
//                    result = add_Contest(text1, type, deadline, expected_amount, "King", 5, "test.png");
//                } else Toast.makeText(getContext(), "請輸入比賽名稱", Toast.LENGTH_SHORT).show();
//
//
//                //  2用戶和該競賽的ATTENDANCE
//                if(result) {
//                    add_Attendance(使用者名稱, text1, 0);
//
//                    //新增比賽到本地列表中(IF條件為如果建立成功)
//                    dataList.add(text1);
//                    adapter.updateData(dataList);
//                }
//                !@#$
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
        GetAttendedContestManager AttendedContestManager = new GetAttendedContestManager();
        JSONArray FriendData = AttendedContestManager.getAttendedContest(Name);

        for (int i = 0; i < FriendData.length(); i++) {
            // 获取当前数组元素（一个 JSONObject）
            JSONObject dataObject = null;
            try {
                dataObject = FriendData.getJSONObject(i);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            // 从 dataObject 中获取 participant_name 和 score 值
            String ContestName = null;
            try {
                String contestName = dataObject.getString("contest_name");
                dataList.add(contestName);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

//        //!@#$
//        dataList.add("香菇湯意麵");
//        dataList.add("滷肉飯");
//        dataList.add("餛飩板條");
//        dataList.add("榨菜肉絲米粉");
//        dataList.add("香菇湯意麵");
//        dataList.add("豬肝麵");
//        dataList.add("高麗菜水餃");
        return dataList;
    }
    private void openChangeAvatarDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.change_avatar_window, null);
        builder.setView(dialogView);
        imagePickerDialog = builder.create();

        GridView gridView = dialogView.findViewById(R.id.gridView);
        ImageAdapter adapter = new ImageAdapter(requireContext(), imageList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedImageResId = imageList.get(position);

                // 儲存avatar圖片的名稱到HomeViewModel
                String avatarName = AvatarIdToName(selectedImageResId);

                // updata avatar 到 database
                imagePickerDialog.dismiss();
            }
        });

        imagePickerDialog.show();
    }
    private int AvatarNameToId(String Name) {
        // Get the resource ID of the drawable using its name
        int resourceId = getResources().getIdentifier(Name, "drawable", requireContext().getPackageName());
        return resourceId;
    }
    private String AvatarIdToName(int resourceId) {
        // Get the resource name of the drawable using its ID
        String resourceName = getResources().getResourceEntryName(resourceId);
        return resourceName;
    }
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private List<Integer> imageList;

        public ImageAdapter(Context context, List<Integer> imageList) {
            this.context = context;
            this.imageList = imageList;
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public Integer getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(imageList.get(position));

            return imageView;
        }
    }


}
