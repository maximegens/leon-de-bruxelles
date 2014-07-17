package com.maxime.leondebruxelles.activity;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.fragments.*;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

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

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance and Add the fragment to the 'fragment_container' FrameLayout
            ListLeonBruxellesFragment listLeonFragment = new ListLeonBruxellesFragment();
            listLeonFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listLeonFragment).commit();
        }
    }

    /**
     * Display or update a selected Leon information
     */
    public void onLeonSelected(int position) {

        // Capture the detail fragment from the activity layout
        DetailFragment detailFrag = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        if (detailFrag != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the DetailFragment to update its content
        	detailFrag.updateDetailView(position);

        } else {
        	
            // If the frag is not available, we're in the one-pane layout and must swap frags...
            // Create fragment and give it an argument for the selected leon
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putInt(DetailFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}