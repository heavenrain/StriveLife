package com.example.strivelifeapplication.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strivelifeapplication.R;
import com.example.strivelifeapplication.ui.ChallengerItem;

import java.util.List;

public class ChallengerAdapter extends RecyclerView.Adapter<ChallengerAdapter.ChallengerViewHolder> {

    private List<ChallengerItem> challengerList;

    public ChallengerAdapter(List<ChallengerItem> challengerList) {
        this.challengerList = challengerList;
    }

    @NonNull
    @Override
    public ChallengerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenger_item, parent, false);
        return new ChallengerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengerViewHolder holder, int position) {
        ChallengerItem challengerItem = challengerList.get(position);

        holder.nameTextView.setText(challengerItem.getName());
        holder.scoreTextView.setText(String.valueOf(challengerItem.getScore()));
    }

    @Override
    public int getItemCount() {
        return challengerList.size();
    }

    public static class ChallengerViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView scoreTextView;

        public ChallengerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView_name);
            scoreTextView = itemView.findViewById(R.id.textView_score);
        }
    }

    public void updateData(List<ChallengerItem> newData) {
//        dataList.clear(); // 清空原有数据
//        dataList.addAll(newData); // 添加新数据
        notifyDataSetChanged(); // 通知适配器数据已更新
    }
}