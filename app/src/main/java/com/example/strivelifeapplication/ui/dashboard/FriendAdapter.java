package com.example.strivelifeapplication.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.strivelifeapplication.R;
import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {

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

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.friend_info, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Friend friend = friendList.get(position);
        holder.name.setText(friend.getName());
        return convertView;

    }

}