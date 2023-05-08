package com.bierbock.Challenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
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

        holder.endDate.setText(challenge.getEndDate());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

}


