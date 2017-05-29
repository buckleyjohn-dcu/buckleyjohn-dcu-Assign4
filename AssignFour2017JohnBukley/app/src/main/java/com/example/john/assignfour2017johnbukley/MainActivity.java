package com.example.john.assignfour2017johnbukley;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ViewPager mViewPager;
    FragmentAdapter myFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TabLayout myTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        if(myTabLayout.getTabCount()>0)
        {
            Log.i(TAG, "The tab has been populated");
        }else
        {
            Log.e(TAG,"Error Tab not populated");
        }
        myFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),myTabLayout.getTabCount());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myFragmentAdapter);
        myTabLayout.setupWithViewPager(mViewPager);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(TAG, "User chose the Settings item, show the app settings UI");
                return true;
            case R.id.action_search:
                Log.i(TAG, "User chose the Search action, show a search bar UI");
                return true;
            case R.id.action_send:
                Log.i(TAG, "User chose the Send action from Action Bar");
                return true;
            case R.id.action_add:
                Log.i(TAG, "User chose the Add action from Action Bar");
                return true;
            case R.id.action_share:
                Log.i(TAG, "User chose the Share action from Action Bar");
                return true;
            case R.id.action_feedback:
                Log.i(TAG, "User chose the Feedback action from Action Bar");
                return true;
            case R.id.action_about:
                Log.i(TAG, "User chose the About action from Action Bar");
                return true;
            case R.id.action_quit:
                Log.i(TAG, "User chose the Quit action from Action Bar");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}