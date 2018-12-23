package com.morinscompany.myapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_streets);

        ListView listView = (ListView) findViewById(R.id.listView);

        Resources res = getResources();
        final String[] streets = res.getStringArray(R.array.streets_array);

        ArrayAdapter<String> streetsAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.item,
                        R.id.text1,
                        streets
                );

        listView.setAdapter(streetsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {

                // Generate a message based on the position
                String message = "You clicked on " + streets[position];

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}

