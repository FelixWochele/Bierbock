package com.bierbock.Challenge;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//This class is only used to change the space of items in the recycler view
public class ChallengeItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public ChallengeItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = space;
        outRect.bottom = space;
    }
}

