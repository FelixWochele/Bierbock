package com.bierbock.UserRating;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

import java.util.List;

public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingViewHolder> {

    private List<UserRanking> userRankings;

    public UserRankingAdapter(List<UserRanking> userRankings) {
        this.userRankings = userRankings;
    }

    @NonNull
    @Override
    public UserRankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_ranking_item, parent, false);
        return new UserRankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRankingViewHolder holder, int position) {
        UserRanking userRanking = userRankings.get(position);
        holder.rankingTextView.setText(String.valueOf(userRanking.getRating()));
        holder.usernameTextView.setText(userRanking.getUsername());
        holder.pointsTextView.setText(String.valueOf(userRanking.getPoints()));

        //Highlight own user
        if (userRanking.isOwnUser()) {
            // Apply the highlight to the view holder's views, e.g., set the background color
            holder.itemView.setBackgroundColor(Color.parseColor("#FBBC05")); //yellow color
        } else {
            // Reset the background color for non-highlighted items
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return userRankings.size();
    }

}
