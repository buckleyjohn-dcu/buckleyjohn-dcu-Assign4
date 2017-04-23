package com.example.john.sdaprojectjohnbuckley;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RSSFeedActivity extends AppCompatActivity {
    Button b1;
    EditText description;
    private String finalUrl="http://http://www.rssweather.com/wx/ie/dublin/rss.php";
    private RSSXML obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        description = (EditText) findViewById(R.id.weatherDetail);
        b1=(Button)findViewById(R.id.buttonWeather);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj = new RSSXML(finalUrl);
                obj.fetchXML();

                while(obj.parsingComplete);
                description.setText(obj.getDescription());
                ;
            }
        });
    }
}
