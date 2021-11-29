package com.renu.quiz_assignment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class FragmentDialog extends DialogFragment {

    public interface DialogClickListener{
        void dialogAddQuestions(String city,boolean answer);
    }

    public DialogClickListener listener;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;

    public FragmentDialog() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);

        EditText enterquestions = v.findViewById(R.id.enterquestions);
        Button btn_save = v.findViewById(R.id.btn_save);
        RadioButton true_btu = v.findViewById(R.id.true_btu);
        RadioButton false_btu = v.findViewById(R.id.false_btu);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterquestions.getText()!=null && enterquestions.getText().toString().length()>0){
                    boolean answer=false;
                    if(true_btu.isChecked()){
                        answer=true;
                    }
                    listener.dialogAddQuestions(enterquestions.getText().toString(),answer);
                    dismiss();
                }
            }
        });
        return v;
    }
}