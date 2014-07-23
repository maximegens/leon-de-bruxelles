package com.maxime.leondebruxelles.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.fragments.DetailFragment;
import com.maxime.leondebruxelles.fragments.ListLeonBruxellesFragment;

/**
 * MainActivity.
 * @author Maxime Gens
 *
 */
public class MainActivity extends FragmentActivity implements ListLeonBruxellesFragment.OnLeonSelectedListener {

    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
           ListLeonBruxellesFragment listLeonFragment = new ListLeonBruxellesFragment();
           listLeonFragment.setArguments(getIntent().getExtras());
           getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listLeonFragment).commit();
        }
    }
    
	@Override
	public void onPause() {
		super.onPause();
	}

    /**
     * Display or update a selected Leon information
     */
    public void onLeonSelected(int id) {

        DetailFragment detailFrag = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        if (detailFrag != null) {
        	detailFrag.updateDetailView(id);
        } else {
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putInt(DetailFragment.ARG_ID, id);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}