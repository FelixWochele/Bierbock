package com.bierbock.Challenge;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

public class ChallengeViewHolder extends RecyclerView.ViewHolder {
    TextView description;
    TextView points;
    ProgressBar progressBar;

    public ChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.challenge_description);
        points = itemView.findViewById(R.id.challenge_points);
        progressBar = itemView.findViewById(R.id.challenge_progress);
    }
}
