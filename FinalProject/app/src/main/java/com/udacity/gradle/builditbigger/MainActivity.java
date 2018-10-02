package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nanodegree.swatisingh.javajoker.Joker;
import com.nanodegree.swatisingh.jokeoffice.JokeActivity;
import com.udacity.gradle.builditbigger.MainActivityFragment;

import static android.widget.Toast.makeText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment,new MainActivityFragment()).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new MainActivityFragment());

//        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


  /*  public void tellJoke(View view) {
       new EndpointAsyncTask(this).execute();
        *//*Joker joker = new Joker();

        // Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(this, JokeActivity.class);
        // Put the string in the envelope
        intent.putExtra(getString(R.string.jokeEnvelope), joker.getJokes());
        startActivity(intent);*//*
//        PrgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
//        new EndpointAsyncTask(this,progressBar).execute();
//        makeText(this, joker.tellJoke(), Toast.LENGTH_SHORT).show();
//        new EndpointAsyncTask().execute(this);

    }*/

}
