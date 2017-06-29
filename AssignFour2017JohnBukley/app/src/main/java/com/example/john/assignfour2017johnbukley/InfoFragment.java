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
 *This fragment contains general restaurant details (name, location, opening times etc.)
 * This fragment also contains a floating action button
 */
public class InfoFragment extends Fragment {
    private static final String TAG = "Infofragment";
    String[] addresses ={"pizzaspecial@dcu.ie"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View infoFragmentView = inflater.inflate(R.layout.infofragment, container, false);
        // Floating Action button sends user to email application to send Queries
        FloatingActionButton emailFab = (FloatingActionButton) infoFragmentView.findViewById(R.id.floatingbutton1);
        emailFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i(TAG, "The Add task floating button was clicked");
                Intent emailFabIntent = new Intent(Intent.ACTION_SENDTO);
                emailFabIntent.setData(Uri.parse("mailto:"));
                emailFabIntent.putExtra(Intent.EXTRA_EMAIL,addresses );
                emailFabIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                startActivity(emailFabIntent);
            }
        });

        return infoFragmentView;
    }
}