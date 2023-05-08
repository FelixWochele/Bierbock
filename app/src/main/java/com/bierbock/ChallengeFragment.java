package com.bierbock;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bierbock.BackendFolder.OwnChallenges;
import com.bierbock.BackendFolder.OwnRanking;
import com.bierbock.BackendFolder.OwnScore;
import com.bierbock.Challenge.Challenge;
import com.bierbock.Challenge.ChallengeAdapter;
import com.bierbock.Challenge.ChallengeItemDecoration;
import com.bierbock.databinding.FragmentChallengeBinding;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ChallengeFragment extends Fragment {

    //bindings for properties of fragment_challenge (maybe Challenges, but mostly different statistics):
    private FragmentChallengeBinding binding;

    private ChallengeAdapter challengeAdapter;


    //Values that are going to be changed by the backend:
    private List<Challenge> challenges;



    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChallengeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        //Statistics above the challenges:

        challenges = new ArrayList<>();
        // Add your challenge data here
        challenges.add(new Challenge("Challenge description", 10, 20, 345, ""));


        //Get current context of the Fragment
        Context currentContext = requireContext();

        challengeAdapter = new ChallengeAdapter(currentContext, challenges);

        //Setup recyclerView
        binding.challengeRecyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
        binding.challengeRecyclerView.setAdapter(challengeAdapter);
        binding.challengeRecyclerView.addItemDecoration(new ChallengeItemDecoration(10));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Call backend classes
        OwnChallenges ownChallenges = new OwnChallenges(this);
        OwnRanking ownRanking = new OwnRanking(this);
        OwnScore ownScore = new OwnScore(this);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    //Method to update challenges list
    @SuppressLint("NotifyDataSetChanged")
    public void updateChallenges(List<Challenge> challenges){
        this.challenges.clear();
        this.challenges.addAll(challenges);

        challengeAdapter.notifyDataSetChanged();
    }

    //TODO: Check if working correctly

    public void updateOwnRanking(final int rank, final int userCount) {
        Activity activity = requireActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String tempString = rank + "/" + userCount;
                binding.currentRankingValue.setText(tempString);
            }
        });
    }

    // Method to update currentScore
    public void updateOwnScore(final int points) {
        Activity activity = requireActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.currentScoreValue.setText(String.valueOf(points));
            }
        });
    }

}

