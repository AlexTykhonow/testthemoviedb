package com.Test.themoviedb.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.Test.themoviedb.R;
import com.Test.themoviedb.ui.activity.base.ToolbarActivity;
import com.Test.themoviedb.ui.fragment.SearchMoviesListFragment;

/**
 * Activity to display the movie search results
 */
public class SearchableActivity extends ToolbarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar.setTitle(R.string.search_results_title);
        this.enableBackButton();
        handleIntent(getIntent());
    }

    /**
     * Case this activity is in the stack, a back press to here also works
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d("ORA","SEARCH"+intent.getAction());

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //String query = intent.getStringExtra(SearchManager.QUERY);
            String query = intent.getStringExtra("query");

            Log.d(TAG, "Searched: " + query);

            //create the fragment and add it
            SearchMoviesListFragment firstFragment = new SearchMoviesListFragment();
            Bundle b = new Bundle();
            b.putString("query", query);
            firstFragment.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.content, firstFragment).commit();
        }
    }
}
