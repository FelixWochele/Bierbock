package com.bierbock.BeerHistory;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bierbock.AspectImageView;
import com.bierbock.R;

public class BeerHistoryViewHolder extends RecyclerView.ViewHolder {

    AspectImageView imageView;
    TextView beerNameTextView;
    TextView dateOfDrinkingTextView;

    public BeerHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.beerImageView);
        beerNameTextView = itemView.findViewById(R.id.beer_name);
        dateOfDrinkingTextView = itemView.findViewById(R.id.date_of_drinking);
    }
}
