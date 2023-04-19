package com.bierbock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bierbock.Challenge.Challenge;
import com.bierbock.Challenge.ChallengeAdapter;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChallengeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); //TODO: lookup if it should be changed

        List<Challenge> challenges = new ArrayList<>();
        // Add your challenge data here
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
        challenges.add(new Challenge("Challenge description", 10, 20, 345)); challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));
        challenges.add(new Challenge("Challenge description", 10, 20, 345));

        ChallengeAdapter adapter = new ChallengeAdapter(this.getContext(), R.layout.challenge_item, challenges);

        ListView listView = (ListView) getView().findViewById(R.id.challenge_list);
        listView.setAdapter(adapter);

    }

}

