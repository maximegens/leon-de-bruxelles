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
 * MainActivity : Activity principalement lancée au démarrage de l'application.
 * @author Maxime Gens
 *
 */
public class MainActivity extends ActionBarActivity  implements ListLeonBruxellesFragment.OnLeonSelectedListener{

	ActionBar actionBar;
	ShareActionProvider mShareActionProvider;
	ListLeonBruxellesFragment listLeonFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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
	
	/** 
	 * Creation du Menu.
	 **/
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    /** 
     * Gestion des items du Menu.
     **/
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
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }

    /**
     * Affiche et met à jour le fragment contenant le détails d'un restaurant.
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