package com.bierbock.UserRating;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

public class UserRankingViewHolder extends RecyclerView.ViewHolder {
    TextView rankingTextView;

    TextView pointsTextView;
    TextView usernameTextView;

    public UserRankingViewHolder(@NonNull View itemView) {
        super(itemView);
        rankingTextView = itemView.findViewById(R.id.ranking_text);
        pointsTextView = itemView.findViewById(R.id.points_text);
        usernameTextView = itemView.findViewById(R.id.username_text);
    }
}


