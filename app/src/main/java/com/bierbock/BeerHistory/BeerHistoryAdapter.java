package com.bierbock.BeerHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.BackendFolder.ImageLoader;
import com.bierbock.HomeFragment;
import com.bierbock.R;

import java.util.List;

public class BeerHistoryAdapter extends RecyclerView.Adapter<BeerHistoryViewHolder> {

    private final Context context;
    private final Fragment fragment;
    private final List<BeerHistoryItem> beerHistoryItems;
    private int targetWidth;
    private int targetHeight;

    public BeerHistoryAdapter(List<BeerHistoryItem> beerHistoryItems, Context context, Fragment fragment) {
        this.beerHistoryItems = beerHistoryItems;
        this.context = context;
        this.fragment = fragment;
        targetWidth = 100; //Default width
        targetHeight = 100; //Default height
    }

    @NonNull
    @Override
    public BeerHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beer_history_item, parent, false);
        return new BeerHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerHistoryViewHolder holder, int position) {
        BeerHistoryItem beerHistoryItem = beerHistoryItems.get(position);

        //Get beer Name, Brand and dateOfDrinking:
        holder.beerNameTextView.setText(String.valueOf(beerHistoryItem.getBeerName()));
        holder.beerBrandTextView.setText(String.valueOf(beerHistoryItem.getBeerBrand()));
        holder.dateOfDrinkingTextView.setText(String.valueOf(beerHistoryItem.getDate()));
        holder.timeTextView.setText(String.valueOf(beerHistoryItem.getTime()));
        holder.locationView.setText(String.valueOf(beerHistoryItem.getLocation()));

        //for initial setup
        holder.imageView.setImageResource(R.drawable.beer_example_image);

        //Get image data
        String imageUrl = beerHistoryItem.getBeerImageURL();
        targetWidth = holder.imageView.getWidth();
        targetHeight = holder.imageView.getHeight();


        //Disable elements while loading:
        //((HomeFragment) fragment).toggleViewElements(false);

        //Set the callback:
        ImageLoader.ImageLoadCallback callback = () -> {

            //Could define callback if images are loaded
        };

        //load the image:
        ImageLoader.loadImage(
                fragment,
                imageUrl,
                holder.imageView,
                targetWidth,
                targetHeight,
                R.drawable.beer_example_image,
                callback
        );
    }
    @Override
    public int getItemCount() {
        return beerHistoryItems.size();
    }

}