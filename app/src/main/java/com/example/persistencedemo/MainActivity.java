package com.example.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private static final String SHARED_PREF = "name";

    private static final String KEY_NAME = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        SharedPreferences sharedPreferences = this.getSharedPreferences( SHARED_PREF, MODE_PRIVATE );

        sharedPreferences.edit().putString( KEY_NAME, "evneet" ).apply();

        //read from shared preferences

        String name = sharedPreferences.getString( KEY_NAME,"Rizul" );

        Log.i( TAG, "onCreate: " + name );


        ArrayList<String> names = new ArrayList<>( Arrays.asList( "Rizul", "Kuldeep", "Anmol", "Ritik"  ) );
//        sharedPreferences.edit().putStringSet( "array", new HashSet<String>( names ) ).apply();
//
//
//        Set<String> namenew = sharedPreferences.getStringSet( "array",new HashSet<String>(  ) );
//
//        Log.i( TAG, "onCreate: " + namenew.toString() );

        try {
            sharedPreferences.edit().putString("names", ObjectSerializer.serialize( names ) ).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> recvNames = new ArrayList<>(  );
        try {
            recvNames = (ArrayList) ObjectSerializer.deserialize( sharedPreferences.getString( "names", ObjectSerializer.serialize( new ArrayList<>(  ) ) ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i( TAG, "onCreate: " + recvNames.toString() );


    }
}
