package com.bierbock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bierbock.BeerHistory.BeerHistoryAdapter;
import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.UserRating.UserRating;
import com.bierbock.UserRating.UserRatingAdapter;
import com.bierbock.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //bindings for properties of fragment_home
    private FragmentHomeBinding binding;

    private RecyclerView homeRecyclerView;
    private UserRatingAdapter userRatingAdapter;
    private List<UserRating> userRatings;

    private BeerHistoryAdapter beerHistoryAdapter;
    private List<BeerHistoryItem> beerHistoryItems;




    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Sample data for user ratings
        userRatings = new ArrayList<>();
        userRatings.add(new UserRating("User1", 1000));
        userRatings.add(new UserRating("User2", 900));
        userRatings.add(new UserRating("User3", 800));

        //Sample data for beer history items
        //TODO: add either URL for the image or the image itself
        //int beerHistoryItemsCount = 4;
        beerHistoryItems = new ArrayList<>();
        beerHistoryItems.add(new BeerHistoryItem("Beer 1", "23/01/2016", "https://images.openfoodfacts.org/images/products/311/978/025/9625/front_fr.84.400.jpg"));
        beerHistoryItems.add(new BeerHistoryItem("Beer 1", "23/01/2017", "https://images.openfoodfacts.org/images/products/311/978/025/9625/front_fr.84.400.jpg"));
        beerHistoryItems.add(new BeerHistoryItem("Beer 1", "23/01/2018", "https://images.openfoodfacts.org/images/products/311/978/025/9625/front_fr.84.400.jpg"));
        beerHistoryItems.add(new BeerHistoryItem("Beer 1", "23/01/2019", "https://images.openfoodfacts.org/images/products/311/978/025/9625/front_fr.84.400.jpg"));

        //initialize the recycler view
        homeRecyclerView = binding.homeRecyclerView;
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Setup adapters:
        beerHistoryAdapter = new BeerHistoryAdapter(beerHistoryItems, getContext(), this);
        userRatingAdapter = new UserRatingAdapter(userRatings);

        //setup the initial adapter for the list
        homeRecyclerView.setAdapter(beerHistoryAdapter);

        //Setup the buttons on click listeners:
        binding.beerHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeRecyclerView.setAdapter(beerHistoryAdapter);
            }
        });

        binding.userRatingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeRecyclerView.setAdapter(userRatingAdapter);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO:NEW stuff:
        updateRecyclerViewData();
        //TODO:NEW stuff end
    }

    private void updateRecyclerViewData() {

        // Hide and disable view elements
        toggleViewElements(false);

        // Reset the imagesLoaded counter
        beerHistoryAdapter.setImagesLoaded(0);
    }

    //Toggle the state of View Elements on the home view:
    public void toggleViewElements(boolean state){

        ProgressBar progressBar = binding.globalProgressBar;

        int visibility = state ? View.VISIBLE : View.INVISIBLE;

        binding.currentUserRating.setVisibility(visibility);
        binding.otherStats.setVisibility(visibility);

        binding.beerHistoryButton.setVisibility(visibility);
        binding.beerHistoryButton.setEnabled(state);
        binding.userRatingsButton.setVisibility(visibility);
        binding.userRatingsButton.setEnabled(state);

        binding.homeRecyclerView.setVisibility(visibility);

        if(!state){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }
}