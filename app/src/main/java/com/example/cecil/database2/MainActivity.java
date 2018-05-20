package com.example.cecil.database2;

import android.arch.persistence.room.Room;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.cecil.database2.R.id.fragment_container;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, AddMadFragment.OnFragmentInteractionListener,
        ReadMadFragment.OnFragmentInteractionListener, UpdateFragment.OnFragmentInteractionListener, ForsideFragment.OnFragmentInteractionListener {
    public static FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class, "userdb").allowMainThreadQueries().build();


        if(findViewById(R.id.fragment_container)!= null)
        {
            if(savedInstanceState!= null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(fragment_container, new ForsideFragment()).commit();
        }

        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
