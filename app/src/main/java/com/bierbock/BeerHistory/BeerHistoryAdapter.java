package com.bierbock.BeerHistory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.AspectImageView;
import com.bierbock.HomeFragment;
import com.bierbock.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class BeerHistoryAdapter extends RecyclerView.Adapter<BeerHistoryViewHolder> {

    private Context context;
    private Fragment fragment;
    private List<BeerHistoryItem> beerHistoryItems;

    private int imagesLoaded = 0;

    private int targetWidth = 100;
    private int targetHeight = 100;

    public BeerHistoryAdapter(List<BeerHistoryItem> beerHistoryItems, Context context, Fragment fragment) {
        this.beerHistoryItems = beerHistoryItems;
        this.context = context;
        this.fragment = fragment;
    }

    public int getImagesLoaded() {
        return imagesLoaded;
    }

    public void setImagesLoaded(int imagesLoaded){
        this.imagesLoaded = imagesLoaded;
    }
    @NonNull
    @Override
    public BeerHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_history_item, parent, false);
        return new BeerHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerHistoryViewHolder holder, int position) {
        BeerHistoryItem beerHistoryItem = beerHistoryItems.get(position);

        //Get beer Name and dateOfDrinking:
        holder.beerNameTextView.setText(String.valueOf(beerHistoryItem.getBeerName()));
        holder.dateOfDrinkingTextView.setText(String.valueOf(beerHistoryItem.getDateTime()));

        //TODO: NEW CODE FROM HERE:....
        String imageUrl = beerHistoryItem.getBeerImageURL();

        //TODO: maybe check images in the home fragment...
        //TODO: understand the callback?
 /*
        loadImage(imageUrl, holder, () -> {
            // Increment the counter and check if all images are loaded
            imagesLoaded++;
            if (imagesLoaded == beerHistoryItems.size()) {
                // Show and enable other elements
                ((HomeFragment) fragment).toggleViewElements(true);
            }
        }); */
    }
    @Override
    public int getItemCount() {
        return beerHistoryItems.size();
    }

    //interface for the image load callback
    interface ImageLoadCallback {
        void onImageLoaded();
    }

    private void loadImage(String imageUrl, @NonNull BeerHistoryViewHolder holder, BeerHistoryAdapter.ImageLoadCallback callback) {

        Glide.with(fragment)
                .load(imageUrl)
                .fallback(R.drawable.beer_example_image) //If the image loading failed
                .override(targetWidth, targetHeight)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        callback.onImageLoaded();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        callback.onImageLoaded();
                        return false;
                    }
                })
                .into(holder.imageView);
    }

}