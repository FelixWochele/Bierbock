package com.bierbock.Challenge;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

public class ChallengeViewHolder extends RecyclerView.ViewHolder {
    TextView description;
    TextView points;
    ProgressBar progressBar;
    TextView endDate;

    //TODO: maybe change the button to a downwards error or "More" button that shows a list of partial progresses

    public ChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.challenge_description);
        points = itemView.findViewById(R.id.challenge_points);
        progressBar = itemView.findViewById(R.id.challenge_progress);
        endDate = itemView.findViewById(R.id.endDate);
    }
}
