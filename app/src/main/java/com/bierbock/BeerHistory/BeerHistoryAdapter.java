package com.bierbock.BeerHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.AspectImageView;
import com.bierbock.R;
import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.BeerHistory.BeerHistoryViewHolder;

import java.util.List;

public class BeerHistoryAdapter extends RecyclerView.Adapter<BeerHistoryViewHolder> {

    private List<BeerHistoryItem> beerHistoryItems;

    public BeerHistoryAdapter(List<BeerHistoryItem> beerHistoryItems) {
        this.beerHistoryItems = beerHistoryItems;
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
        holder.beerNameTextView.setText(String.valueOf(beerHistoryItem.getBeerName()));
        holder.dateOfDrinkingTextView.setText(String.valueOf(beerHistoryItem.getDateTime()));
       // holder.imageView = (AspectImageView) beerHistoryItem.getBeerImage(); //TODO: implement some logic here?
    }
    @Override
    public int getItemCount() {
        return beerHistoryItems.size();
    }

}

