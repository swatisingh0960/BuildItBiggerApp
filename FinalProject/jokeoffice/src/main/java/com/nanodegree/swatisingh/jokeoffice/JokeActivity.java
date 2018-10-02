package com.nanodegree.swatisingh.jokeoffice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static String JOKE_INTENT = "Joke_Intent ";
    String LOG_TAG = "FREEDEBUG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

//            Log.i(LOG_TAG, joke);
            /*TextView textView = findViewById(R.id.joke_text);

        String JokeResult = null;

        Intent intent = getIntent();

        JokeResult = intent.getStringExtra(getString(R.string.jokeEnvelope));

        if (JokeResult != null){
            textView.setText(JokeResult);
        } else {
            textView.setText("Dig deeper, we gotto find a joke!!");
        }
    } */
            if (getIntent().hasExtra(JOKE_INTENT)){
                String joke = getIntent().getStringExtra(JOKE_INTENT);
                TextView textView = findViewById(R.id.joke_text);
                textView.setText(joke);
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

