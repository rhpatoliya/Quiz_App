package com.renu.quiz_assignment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends Fragment
{
    static String TAG="QuestionsFragment";
    QuestionsBank questionsBank = new QuestionsBank();
    ArrayList<Questions> questionsList = new ArrayList<>();
    TextView questions_container;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "questionsid";
    private static final String ARG_PARAM2 = "colorid";

    private int mParam1;
    private String mParam2;


    public QuestionsFragment()
    {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance(int questionsid, String colorid)
    {
        QuestionsFragment fragment = new QuestionsFragment();
        Log.e(TAG,"Value questionsid="+questionsid);
        Log.e(TAG,"Value colorid="+colorid);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, questionsid);
        args.putString(ARG_PARAM2, colorid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        questions_container=view.findViewById(R.id.questions_container);
        questions_container.setText(this.getResources().getString(mParam1));
        questions_container.setBackgroundColor(Color.parseColor(mParam2));
        return view;
    }

}