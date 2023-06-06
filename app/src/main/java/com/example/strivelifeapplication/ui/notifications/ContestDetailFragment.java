//package com.example.strivelifeapplication.ui.notifications;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import androidx.fragment.app.Fragment;
//
//import com.example.strivelifeapplication.R;
//
//public class ContestDetailFragment extends Fragment{
//    public static ContestDetailFragment newInstance(String selectedItem) {
//        ContestDetailFragment fragment = new ContestDetailFragment();
//        Bundle args = new Bundle();
//        args.putString("selectedItem", selectedItem);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.contest_detail, container, false);
//        TextView titleTextView = view.findViewById(R.id.textView_contest_name);
//
//        Bundle args = getArguments();
//        if (args != null) {
//            String selectedItem = args.getString("selectedItem");
//            titleTextView.setText(selectedItem);
//        }
//
//        // 返回按钮的点击事件
//        Button backButton = view.findViewById(R.id.button_back);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 关闭ContestDetailFragment并返回到原先的Fragment
//                requireActivity().getSupportFragmentManager().popBackStack();
//            }
//        });
//
//        return view;
//    }
//}
