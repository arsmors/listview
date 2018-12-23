package com.morinscompany.myapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_streets);

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();

        OkHttpClient client = new OkHttpClient();
        final List<String> list = new ArrayList<String>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONObject array = new JSONObject(jsonData);

                    for (int i = 0; i < array.length(); i++) {
                        list.add(array.getJSONObject(String.valueOf(i)).getString("id"));
                    }
                } catch (JSONException ex) {
                    Log.e("JSON-PARSE", ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

        ListView listView = (ListView) findViewById(R.id.listView);

//        Resources res = getResources();
//        final String[] streets = res.getStringArray(R.array.streets_array);

        ArrayAdapter<String> jsonAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.item,
                        R.id.text1,
                        list
                );

        listView.setAdapter(jsonAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {

                // Generate a message based on the position
                String message = "You clicked on " + list.get(position);

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}

