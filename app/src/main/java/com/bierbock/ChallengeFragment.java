package com.bierbock;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.bierbock.Challenge.Challenge;
import com.bierbock.Challenge.ChallengeAdapter;
import com.bierbock.Challenge.ChallengeItemDecoration;
import com.bierbock.databinding.FragmentChallengeBinding;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textview.MaterialTextView;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChallengeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeFragment extends Fragment {

    //bindings for properties of fragment_challenge (maybe Challenges, but mostly different statistics):
    private FragmentChallengeBinding binding;

    private FrameLayout progressContainer;

    private List<Challenge> challenges;
    private ChallengeAdapter challengeAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChallengeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengeFragment newInstance(String param1, String param2) {
        ChallengeFragment fragment = new ChallengeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChallengeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Statistics above the challenges:
        MaterialTextView statistic1Title = binding.statistic1Title;
        MaterialTextView statistic1Value = binding.statistic1Value;
        MaterialTextView statistic2Title = binding.statistic2Title;
        MaterialTextView statistic2Value = binding.statistic2Value;

        challenges = new ArrayList<>();
        // Add your challenge data here
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 40, 345));
        challenges.add(new Challenge("Challenge description", 10, 100, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));

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
}

