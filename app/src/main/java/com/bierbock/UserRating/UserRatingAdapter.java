package com.bierbock.UserRating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
    }

    @Override
    public int getItemCount() {
        return userRatings.size();
    }

}
