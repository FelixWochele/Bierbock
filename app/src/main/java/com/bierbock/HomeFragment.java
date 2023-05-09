package com.bierbock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.bierbock.BackendFolder.OwnDrinkProgress;
import com.bierbock.BackendFolder.OwnDrinkProgress;
import com.bierbock.BackendFolder.TopRankedUsers;
import com.bierbock.BeerHistory.BeerHistoryAdapter;
import com.bierbock.BeerHistory.BeerHistoryItem;
import com.bierbock.UserRating.UserRanking;
import com.bierbock.UserRating.UserRankingAdapter;
import com.bierbock.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //bindings for properties of fragment_home
    private FragmentHomeBinding binding;

    private RecyclerView homeRecyclerView;
    private UserRankingAdapter userRankingAdapter;
    private List<UserRanking> userRankings;

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

        toggleViewElements(true);

        binding.nameOfValues.setVisibility(View.INVISIBLE);

        // Sample data for user ratings
        userRankings = new ArrayList<>();
        userRankings.add(new UserRanking("User1", 1000, 10));

        //Sample data for beer history items
        beerHistoryItems = new ArrayList<>();
        beerHistoryItems.add(new BeerHistoryItem("Beer 1", "Beer brand 1","23/01/2016", "13:31", "Horb am Neckar", "https://images.openfoodfacts.org/images/products/311/978/025/9625/front_fr.84.400.jpg"));

        //initialize the recycler view
        homeRecyclerView = binding.homeRecyclerView;

        Context currentContext = requireContext();

        homeRecyclerView.setLayoutManager(new LinearLayoutManager(currentContext));

        //Setup adapters:
        beerHistoryAdapter = new BeerHistoryAdapter(beerHistoryItems, currentContext, this);
        userRankingAdapter = new UserRankingAdapter(userRankings);

        //setup the initial adapter for the list
        homeRecyclerView.setAdapter(beerHistoryAdapter);

        //Set bold Text
        binding.beerHistrory.setTypeface(null, Typeface.BOLD);
        binding.beerHistrory.setTextColor(Color.parseColor("#FF9800"));
        binding.userRanking.setTypeface(null, Typeface.NORMAL);
        binding.userRanking.setTextColor(Color.parseColor("#666666"));

        //Gesture
        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                System.out.println("Right to Left");

                                if(homeRecyclerView.getAdapter() != userRankingAdapter){

                                    //Set bold Text
                                    binding.userRanking.setTypeface(null, Typeface.BOLD);
                                    binding.userRanking.setTextColor(Color.parseColor("#FF9800"));
                                    binding.beerHistrory.setTypeface(null, Typeface.NORMAL);
                                    binding.beerHistrory.setTextColor(Color.parseColor("#666666"));
                                    binding.nameOfValues.setVisibility(View.VISIBLE);
                                    binding.BeerCountAll.setVisibility(View.INVISIBLE);


                                    homeRecyclerView.setAdapter(userRankingAdapter);
                                    callTopRankedUsers();
                                }

                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                System.out.println("Left to Right");

                                //If statement to not update the view on every button click
                                if(homeRecyclerView.getAdapter() != beerHistoryAdapter){

                                    ///Set bold Text
                                    binding.beerHistrory.setTypeface(null, Typeface.BOLD);
                                    binding.beerHistrory.setTextColor(Color.parseColor("#FF9800"));
                                    binding.userRanking.setTypeface(null, Typeface.NORMAL);
                                    binding.userRanking.setTextColor(Color.parseColor("#666666"));
                                    binding.nameOfValues.setVisibility(View.INVISIBLE);
                                    binding.BeerCountAll.setVisibility(View.VISIBLE);

                                    homeRecyclerView.setAdapter(beerHistoryAdapter);
                                    callOwnDrinkProgress();
                                }

                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        //Call gesture on touch
        homeRecyclerView.setOnTouchListener((v, event) -> gesture.onTouchEvent(event));

        return view;
    }

    //Toggle the state of View Elements on the home view:
    public void toggleViewElements(boolean state){

        ProgressBar progressBar = binding.globalProgressBar;

        int visibility = state ? View.VISIBLE : View.INVISIBLE;

        progressBar.setVisibility(visibility);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callOwnDrinkProgress();
    }

    private void callOwnDrinkProgress(){
        //create ownDrinkProgress here and call the updateBeerHistory method:
        OwnDrinkProgress ownDrinkProgress = new OwnDrinkProgress(this);
    }

    private void callTopRankedUsers(){
        TopRankedUsers topRankedUsers = new TopRankedUsers(this);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateBeerHistory(List<BeerHistoryItem> beerHistoryItems){
        this.beerHistoryItems.clear();
        this.beerHistoryItems.addAll(beerHistoryItems);

        String beerCountAll = "Beer Count: " + beerHistoryItems.size();

        binding.BeerCountAll.setText(beerCountAll);

        beerHistoryAdapter.notifyDataSetChanged();

        toggleViewElements(false);
    }

    //Method to update the user Ranking list:
    @SuppressLint("NotifyDataSetChanged")
    public void updateUserRankings(List<UserRanking> userRankings, String ownUserName, int ownRank, int ownPoints){

        boolean ownUserInTop25 = false;

        this.userRankings.clear();
        //Add all user ratings and set the ownUser property, if own user is in the top 25
        for (UserRanking userRanking : userRankings) {
            if (userRanking.getUsername().equals(ownUserName)) {
                userRanking.setOwnUser(true);
                ownUserInTop25 = true;
            }
            this.userRankings.add(userRanking);
        }

        if (!ownUserInTop25) {
            UserRanking ownRanking = new UserRanking(ownUserName, ownRank, ownPoints);
            ownRanking.setOwnUser(true); //to set own user property
            this.userRankings.add(ownRanking);
        }

        userRankingAdapter.notifyDataSetChanged();
    }

    public void backendErrorMessage(){
        Toast.makeText(this.getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
    }


}