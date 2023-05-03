package com.bierbock.BeerHistory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.R;

public class BeerHistoryViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView beerNameTextView;
    TextView beerBrandTextView;
    TextView dateOfDrinkingTextView;

    public BeerHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.drank_beer_image);
        beerNameTextView = itemView.findViewById(R.id.beer_name);
        beerBrandTextView = itemView.findViewById(R.id.beer_brand);
        dateOfDrinkingTextView = itemView.findViewById(R.id.date_of_drinking);
    }
}
