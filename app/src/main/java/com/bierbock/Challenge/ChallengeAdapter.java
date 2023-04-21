package com.bierbock.Challenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeViewHolder> {
    private Context context;
    private List<Challenge> challenges;

    public ChallengeAdapter(Context context, List<Challenge> challenges) {
        this.context = context;
        this.challenges = challenges;
    }

    @NonNull
    @Override
    public ChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.challenge_item, parent, false);
        return new ChallengeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeViewHolder holder, int position) {
        Challenge challenge = challenges.get(position);
        holder.description.setText(challenge.getDescription());
        holder.points.setText(String.valueOf(challenge.getPoints()));
        holder.progressBar.setMax(challenge.getMaxProgress());
        holder.progressBar.setProgress(challenge.getProgress());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }
}




/*
public class ChallengeAdapter extends ArrayAdapter<Challenge> {
    private Context context;
    private List<Challenge> challenges;

    public ChallengeAdapter(@NonNull Context context, int resource, @NonNull List<Challenge> objects) {
        super(context, resource, objects);
        this.context = context;
        this.challenges = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.challenge_item, parent, false);

        TextView description = rowView.findViewById(R.id.challenge_description);
        TextView points = rowView.findViewById(R.id.challenge_points);
        ProgressBar progressBar = rowView.findViewById(R.id.challenge_progress);

        Challenge challenge = challenges.get(position);
        description.setText(challenge.getDescription());
        points.setText(String.valueOf(challenge.getPoints()));
        progressBar.setMax(challenge.getMaxProgress());
        progressBar.setProgress(challenge.getProgress());

        return rowView;
    }
} */

