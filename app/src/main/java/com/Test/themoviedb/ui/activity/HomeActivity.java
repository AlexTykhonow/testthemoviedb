package com.Test.themoviedb.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;

import com.Test.themoviedb.data.exception.FailedGettingDataException;
import com.Test.themoviedb.data.repository.base.ICloudMovieRepository;
import com.Test.themoviedb.data.repository.base.ILocalMovieRepository;
import com.Test.themoviedb.data.repository.base.MovieRepositoryFactory;
import com.Test.themoviedb.model.Movie;
import com.Test.themoviedb.model.MovieDetails;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import com.Test.themoviedb.R;
import com.Test.themoviedb.ui.activity.base.ToolbarActivity;
import com.Test.themoviedb.ui.fragment.InTheatersMoviesListFragment;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Launcher activity that displays the tabs and fragments containing the movies lists
 */
public class HomeActivity extends ToolbarActivity implements ViewPager.OnPageChangeListener {

    protected SearchView searchView;
    protected ViewPager viewPager;
    protected AutoCompleteTextView autoText;
    protected int tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setPager();
        this.setSerchText();
    }

    @Override
    protected int getToolbarLayout() {
        return R.layout.activity_home;
    }

    /**
     * auto complite
     */
    private void setSerchText(){
        autoText = (AutoCompleteTextView) findViewById(R.id.actv);
        ArrayList<String> title = new ArrayList<>();
        ILocalMovieRepository localRepo = MovieRepositoryFactory.getLocalRepository(getApplicationContext());
       // Log.d("ora"," {} "+localRepo.getTheatersMovies()+" /**/ ");
        for(int i = 0;i<localRepo.getTheatersMovies().size();i++){
            Movie movie = localRepo.getTheatersMovies().get(i);
           // Log.d("ora"," {} "+movie.getOriginalTitle());
            title.add(movie.getOriginalTitle());
        }

        autoText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    default: 
                        startSearch(autoText.getText().toString());
                        break;
                }
                return false;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_auto, R.id.text_view_list_item, title);
        autoText.setAdapter(adapter);
    }
    private void startSearch(String value){
        Intent myIntent = new Intent(HomeActivity.this, SearchableActivity.class);
        myIntent.putExtra("query", value); //Optional parameters
        //this.startActivity(myIntent);

    }
    private void setPager() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                                  .add(R.string.page_theaters,
                                       InTheatersMoviesListFragment.class)
                                  .create());

        //Set the fragments pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Set the tabs names pager
        /*SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabs);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(this);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Set a call to Searchable activity from the search widget
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //Expand the search widget
        searchView.setIconifiedByDefault(true);

        return true;
    }

    /**
     * Handle action bar options clicks
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    /*
   |--------------------------------------------------------------------------
   | Save state
   |--------------------------------------------------------------------------
   */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int pos = savedInstanceState.getInt("tab_pos");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Clear the search query and remove keyboard on back from the search activity
        searchView.setQuery("", false);
        searchView.clearFocus();

        outState.putInt("tab_pos", tabPosition);

        super.onSaveInstanceState(outState);
    }
}
