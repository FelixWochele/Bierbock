package com.bierbock.UserRating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

import java.util.List;

public class UserRatingViewHolder extends RecyclerView.ViewHolder {
    TextView ratingTextView;

    TextView pointsTextView;
    TextView usernameTextView;

    public UserRatingViewHolder(@NonNull View itemView) {
        super(itemView);
        ratingTextView = itemView.findViewById(R.id.rating_text);
        pointsTextView = itemView.findViewById(R.id.points_text);
        usernameTextView = itemView.findViewById(R.id.username_text);
    }
}


