package com.example.john.assignfour2017johnbukley;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 *
 */
public class InfoFragment extends Fragment {
    private static final String TAG = "Infofragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View infoFragmentView = inflater.inflate(R.layout.infofragment, container, false);

        FloatingActionButton emailFab = (FloatingActionButton) infoFragmentView.findViewById(R.id.floatingbutton1);
        emailFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i(TAG, "The Add task floating button was clicked");
                Intent emailFabIntent = new Intent(Intent.ACTION_SENDTO);
                emailFabIntent.setData(Uri.parse("mailto:"));
                emailFabIntent.putExtra(Intent.EXTRA_EMAIL, "pizzaspecial@dcu.ie");
                emailFabIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                startActivity(emailFabIntent);
            }
        });
        return infoFragmentView;
    }
}