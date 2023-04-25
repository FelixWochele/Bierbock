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

        //New from here:
       /* Bitmap beerBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.beerpattern);
        Bitmap roundedBeerBitmap = getRoundedCornerBitmap(beerBitmap, 12);
        BitmapDrawable roundedBeerDrawable = new BitmapDrawable(context.getResources(), roundedBeerBitmap);

        ClipDrawable progressClipDrawable = new ClipDrawable(roundedBeerDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
        LayerDrawable progressDrawable = new LayerDrawable(new Drawable[]{progressClipDrawable});
        holder.progressBar.setProgressDrawable(progressDrawable); */
    }
//holder.progressBar.setMax(challenge.getMaxProgress());
    //holder.progressBar.setProgress(progress);
    //holder.progressBar.animateBubble();


    // holder.progressBar.setProgress(challenge.getProgress());
    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
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

