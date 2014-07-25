package com.maxime.leondebruxelles.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.maxime.leondebruxelles.R;
import com.maxime.leondebruxelles.fragments.DetailFragment;
import com.maxime.leondebruxelles.fragments.ListLeonBruxellesFragment;

/**
 * MainActivity.
 * @author Maxime Gens
 *
 */
public class MainActivity extends ActionBarActivity  implements ListLeonBruxellesFragment.OnLeonSelectedListener{

	ActionBar actionBar;
	ShareActionProvider mShareActionProvider;
	ListLeonBruxellesFragment listLeonFragment;
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
            
           actionBar = getSupportActionBar();
           
           FragmentTransaction t = getSupportFragmentManager().beginTransaction();
           listLeonFragment = new ListLeonBruxellesFragment();
           listLeonFragment.setArguments(getIntent().getExtras());
           t.add(R.id.fragment_container, listLeonFragment).commit();
        }
    }
    
	@Override
	public void onPause() {
		super.onPause();
	}
	
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	AlertDialogCustom alert = new AlertDialogCustom(this);
    	switch (item.getItemId()) {
    	case R.id.menu_website :
			alert.showWebSite();
    		return true;
    	case R.id.menu_geo :
    		alert.activateGPS();
    		return true;
    	case R.id.menu_refresh :
    		listLeonFragment.updateListLeon();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
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