package com.bierbock.Challenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bierbock.R;

import java.util.List;

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
}

