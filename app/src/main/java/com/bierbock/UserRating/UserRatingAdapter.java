package com.bierbock.UserRating;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

import java.util.List;

public class UserRatingAdapter extends RecyclerView.Adapter<UserRatingViewHolder> {

    private List<UserRating> userRatings;

    public UserRatingAdapter(List<UserRating> userRatings) {
        this.userRatings = userRatings;
    }

    @NonNull
    @Override
    public UserRatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rating_item, parent, false);
        return new UserRatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRatingViewHolder holder, int position) {
        UserRating userRating = userRatings.get(position);
        holder.ratingTextView.setText(String.valueOf(userRating.getRating()));
        holder.usernameTextView.setText(userRating.getUsername());
        holder.pointsTextView.setText(String.valueOf(userRating.getPoints()));

        //Highlight own user
        if (userRating.isOwnUser()) {
            // Apply the highlight to the view holder's views, e.g., set the background color
            holder.itemView.setBackgroundColor(Color.parseColor("#FBBC05")); //yellow color
        } else {
            // Reset the background color for non-highlighted items
            holder.itemView.setBackgroundColor(Color.parseColor("#666666"));
        }
    }

    @Override
    public int getItemCount() {
        return userRatings.size();
    }

}
