package com.example.strivelifeapplication.ui.notifications;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
public class ChallengerDecoration extends RecyclerView.ItemDecoration{
    private final int spacing;

    public ChallengerDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = spacing;
        outRect.bottom = spacing;
    }
}
