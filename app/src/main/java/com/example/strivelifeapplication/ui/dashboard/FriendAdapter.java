package com.example.strivelifeapplication.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.strivelifeapplication.R;
import com.example.strivelifeapplication.ui.home.HomeFragment;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter<Friend> {

    private ArrayList<Friend> friendList;
    private Context context;

    public FriendAdapter(Context context, int textViewResourceId,
                         ArrayList<Friend> friendList) {
        super(context, textViewResourceId, friendList);
        this.context = context;
        this.friendList = new ArrayList<Friend>();
        this.friendList.addAll(friendList);
    }

    private class ViewHolder {
        TextView name;
        ImageView avatar;
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
            holder.avatar = (ImageView) convertView.findViewById(R.id.myImageView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Friend friend = friendList.get(position);
        holder.name.setText(friend.getName());
        holder.avatar.setImageResource(AvatarNameToId(friend.getAvatar()));
        return convertView;

    }
    public int AvatarNameToId(String Name) {
        // Get the resource ID of the drawable using its name
        int resourceId = context.getResources().getIdentifier(Name, "drawable", context.getPackageName());
        return resourceId;
    }

}