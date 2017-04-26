/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.john.sdaprojectjohnbuckley;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Citation:
 * URL:https://www.tutorialspoint.com/android/android_rss_reader.htm
 * Retrieved on 17th of March 2017
 */
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
