package com.bierbock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bierbock.UserRating.UserRating;
import com.bierbock.UserRating.UserRatingAdapter;
import com.bierbock.databinding.FragmentChallengeBinding;
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

    private RecyclerView userRatingList;
    private UserRatingAdapter userRatingAdapter;
    private List<UserRating> userRatings;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Sample data for user ratings
        userRatings = new ArrayList<>();
        userRatings.add(new UserRating("User1", 1000));
        userRatings.add(new UserRating("User2", 900));
        userRatings.add(new UserRating("User3", 800));

        //TODO: Setup Recycler View here

        userRatingList = binding.userDataList;
        userRatingAdapter = new UserRatingAdapter(userRatings);

        userRatingList.setLayoutManager(new LinearLayoutManager(getContext()));
        userRatingList.setAdapter(userRatingAdapter);

        return view;
    }
}