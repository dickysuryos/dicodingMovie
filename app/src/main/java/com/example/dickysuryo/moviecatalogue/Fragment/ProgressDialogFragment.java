package com.example.dickysuryo.moviecatalogue.Fragment;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.dickysuryo.moviecatalogue.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
   String mTitle;
   final  String  ARG_TITLE = "TITLE";
   public ProgressDialogFragment newInstane(String title){
       ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
       Bundle args = new Bundle();
       args.putString(ARG_TITLE,title);
       progressDialogFragment.setArguments(args);
       return progressDialogFragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
       getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View rootView = inflater.inflate(R.layout.dialog_progress,container,false);


        return rootView;
    }



}
