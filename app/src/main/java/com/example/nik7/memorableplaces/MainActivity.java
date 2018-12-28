package com.example.nik7.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    static ArrayList<String> lats = new ArrayList<>();
    static ArrayList<String> longs = new ArrayList<>();
    static ArrayList<String> Addresess = new ArrayList<>();
    static ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = this.getSharedPreferences("com.example.nik7.memorableplaces", Context.MODE_PRIVATE);

        lats.clear();
        longs.clear();
        Addresess.clear();

        try{
            Log.i("Entered", "First");
            lats = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("lats", ObjectSerializer.serialize(new ArrayList<String>())));
            longs = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("longs", ObjectSerializer.serialize(new ArrayList<String>())));
            Addresess = (ArrayList<String>) ObjectSerializer.deserialize(sp.getString("Addresess", ObjectSerializer.serialize(new ArrayList<String>())));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("First", "time");
        }

        if (!(lats.size() == longs.size() && longs.size() == Addresess.size() && lats.size() > 0)){
            lats.add(null);
            longs.add(null);
            Addresess.add("Add a new place");
        }

        list = findViewById(R.id.list);



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Addresess);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("type", position);
                startActivity(intent);
            }
        });


    }

}
