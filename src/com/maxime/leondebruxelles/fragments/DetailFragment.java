package com.maxime.leondebruxelles.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxime.leondebruxelles.R;

public class DetailFragment extends Fragment {
	
    public final static String ARG_ID = "id";
    int mCurrentId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        if (savedInstanceState != null) {
        	mCurrentId = savedInstanceState.getInt(ARG_ID);
        }
        
        return inflater.inflate(R.layout.detail_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
        	updateDetailView(args.getInt(ARG_ID));
        } else if (mCurrentId != -1) {
        	updateDetailView(mCurrentId);
        }
    }

    public void updateDetailView(int position) {
        TextView article = (TextView) getActivity().findViewById(R.id.article);
        article.setText(String.valueOf(position));
        mCurrentId = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_ID, mCurrentId);
    }
}