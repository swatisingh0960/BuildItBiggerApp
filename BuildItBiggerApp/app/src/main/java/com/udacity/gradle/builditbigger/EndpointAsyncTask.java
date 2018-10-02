package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.nanodegree.swatisingh.jokeoffice.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.MainActivityFragment;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApi = null;
    private MainActivityFragment mainActivityFragment;
    Context  context;

    public EndpointAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... mainActivityFragments) {

  /*      mainActivityFragment = mainActivityFragments[0]; //commented earlier
        context = mainActivityFragments[0].getContext(); //commented earlier*/

        if (myApi == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setRootUrl("http://localhost:8080")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApi = builder.build();
        }
        try {
            return myApi.tellJoke().execute().getJoke();
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e("JOKE", s);

        try {
            Intent intent = new Intent(context, JokeActivity.class);
//
            intent.putExtra(JokeActivity.JOKE_INTENT, "JOKE: " + s.toString());

            context.startActivity(intent);
        }
        catch(Exception e){
e.printStackTrace();
        }
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        context.startActivity(intent); // this will start activity
//        mainActivityFragment.loadedJoke = s; //commented earlier
//        mainActivityFragment.launchDisplayJokeActivity();//commented earlier
    }


}


//        super.onPostExecute(s);


