package com.example.john.assignfour2017johnbukley;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.text.NumberFormat;


/**
 *
 */
public class OrderFragment extends Fragment implements android.widget.CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener
{

    TextView mtextView;
    String mSummary ="";
    int mQuantity = 1;
    double mPrice = 0.0;
    Menu orderMenu;

    private static final String TAG = "OrderFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        Log.i(TAG, "The Orderfragement view has been populated");
        // Inflate the layout for this fragment
        View orderFragmentView = inflater.inflate(R.layout.orderfragment, container, false);
        CheckBox pepperoniCheckBox = (CheckBox) orderFragmentView.findViewById(R.id.checkBox1);
        pepperoniCheckBox.setOnCheckedChangeListener(this);
        CheckBox extraCheeseCheckBox =(CheckBox) orderFragmentView.findViewById(R.id.checkBox2);
        extraCheeseCheckBox.setOnCheckedChangeListener(OrderFragment.this);
        CheckBox onionsCheckBox =(CheckBox)orderFragmentView.findViewById(R.id.checkBox3);
        onionsCheckBox.setOnCheckedChangeListener(OrderFragment.this);
        CheckBox mushroomsCheckBox=(CheckBox) orderFragmentView.findViewById(R.id.checkBox4);
        mushroomsCheckBox.setOnCheckedChangeListener(OrderFragment.this);

        Spinner spinner = (Spinner) orderFragmentView.findViewById(R.id.spinnerInputType);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.ui_number_entries, R.layout.spinner_item);
        if(adapter.getCount()>0)
        {
            Log.i(TAG, "items are in the data set are represented by this Adapter");
        }else
        {
            Log.e(TAG,"Error items are in the data set are represented by this Adapter");
        }
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(OrderFragment.this);

        mtextView = (TextView) orderFragmentView.findViewById(R.id.textView1);
        Button button1 = (Button) orderFragmentView.findViewById(R.id.summaryButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Summary();
            }
        });
        Button button2 = (Button) orderFragmentView.findViewById(R.id.summitOrderButton);
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                submitOrder();
            }
        });
        return orderFragmentView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        StringBuilder str =
                new StringBuilder(mtextView.getText());
        CharSequence boxText = buttonView.getText();
        if (isChecked) {
            str.append(" " + boxText + ",");
        } else {
            int start = str.indexOf(boxText.toString() + ",");
            int length = boxText.length()+ 1;
            str.replace(start, start + length, "");
        }

        mtextView.setText(str.toString().trim());
    }

    public void submitOrder() {

        //incase of caching
        if (getActivity().getIntent()!= null)
        {
            getActivity().getIntent().removeExtra(Intent.EXTRA_TEXT);
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        String summary = "";
        summary = createOrderSummary();
        intent.putExtra(Intent.EXTRA_TEXT, summary);


        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send email..."));
        }
    }

    private double calculatePrice( String toppings) {
        // First calculate the price of pizza
        double basePrice = 10.0;


        // If the user wants Mushrooms, add €1 per topping
        if (toppings.contains("Mushrooms")) {
            basePrice = basePrice + 1 ;
        }

        // If the user wants Pepperoni, add €1 per topping
        if (toppings.contains("Pepperoni")) {
            basePrice = basePrice + 1;

        }
        // If the user wants Extra Cheese, add €1 per topping
        if (toppings.contains("Extra Cheese")) {
            basePrice = basePrice + 1 ;
        }

        // If the user wants Onions, add €1 per topping
        if (toppings.contains("Onions")) {
            basePrice = basePrice + 1;
        }
        // Calculate the total order price by multiplying by the quantity
        return basePrice * mQuantity;
    }

    /**
     * Create summary of the order.
     *

     */

    private String createOrderSummary( ) {

        EditText editText = (EditText) getActivity().findViewById(R.id.name_field);
        String toppings = mtextView.getText().toString();
        mPrice = calculatePrice(toppings);

/** the below text heading , should in the strings.xml file, but taken out to show you the contents */
        String priceMessage = "Name:" + " " + editText.getText().toString();
        priceMessage += "\n" + "Standard Margherita Pizza";
        priceMessage += "\n" + "Extra Toppings: " + mtextView.getText().toString();
        priceMessage += "\n" + "Quantity: " + mQuantity;
        priceMessage += "\n" + "Price: " + NumberFormat.getCurrencyInstance().format(mPrice);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;

        //update screen
    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using

        mQuantity =  pos + 1;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void Summary () {

        Toast.makeText(getActivity(), createOrderSummary(), Toast.LENGTH_LONG).show();

    }

    public void onPrepareOptionsMenu (Menu menu){
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        super.onPrepareOptionsMenu(menu);
    }


}



