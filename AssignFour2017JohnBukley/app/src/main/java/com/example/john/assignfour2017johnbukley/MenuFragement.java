package com.example.john.assignfour2017johnbukley;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * This fragment displays the same content and functionality as was developed in Assignment 3 i.e. Pizza Menu List Activity
 */
public class MenuFragement extends Fragment {
    private static final String TAG = "MenuFragment";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View menuFragmentView = inflater.inflate(R.layout.menufragement, container, false);
        Log.i(TAG,"Menu Fragment has been inflated");
        final ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(new Pizza(R.drawable.bbq, R.string.pizza1,R.string.pizza1Desc));
        pizzas.add(new Pizza(R.drawable.hawaiian, R.string.pizza3,R.string.pizza3Desc));
        pizzas.add(new Pizza(R.drawable.pepperoni, R.string.pizza4,R.string.pizza4Desc));
        pizzas.add(new Pizza(R.drawable.margherita, R.string.pizza5,R.string.pizza5Desc));
        pizzas.add(new Pizza(R.drawable.chicken, R.string.pizza6,R.string.pizza6Desc));

        PizzaAdapter adapter = new PizzaAdapter(getActivity(), pizzas, R.color.category_colors);
        ListView listView = (ListView) menuFragmentView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        // Set a click listener to show the toast message
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Pizza} object at the given position the user clicked on . Use created array
                Pizza word = pizzas.get(position);
                String colourWord = getActivity().getResources().getString(word.getPizzaDescId() );
                Toast.makeText(getActivity().getApplicationContext(), colourWord, Toast.LENGTH_LONG).show();
            }
        });

        return menuFragmentView;
    }
}
