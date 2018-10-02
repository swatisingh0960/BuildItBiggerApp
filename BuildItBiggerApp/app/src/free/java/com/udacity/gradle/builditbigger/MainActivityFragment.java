package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.nanodegree.swatisingh.jokeoffice.JokeActivity;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment(){

    }

    ProgressBar progressBar = null;
    public String loadedJoke = null;
    public boolean testFlag = false;
    PublisherInterstitialAd publisherInterstitialAd = null;
    String LOG_TAG = "FREEDEBUG";


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
         publisherInterstitialAd = new PublisherInterstitialAd(getContext());
         publisherInterstitialAd.setAdUnitId("ca-app-pub-7867604826748291/8122977163");

         publisherInterstitialAd.setAdListener(new AdListener(){
             @Override
             public void onAdClosed() {
                 super.onAdClosed();
                 progressBar.setVisibility(View.VISIBLE);
                 getJoke();

                 requestNewInterstitial();
             }

             @Override
             public void onAdFailedToLoad(int errorCode) {
                 super.onAdFailedToLoad(errorCode);
                 Log.i(LOG_TAG, "onAdFailedToLoad: ad Failed to load. Reloading...");

                 requestNewInterstitial();
             }

             @Override
             public void onAdLoaded() {
                 Log.i(LOG_TAG, "onAdLoaded: interstitial is ready!");
                 super.onAdLoaded();
             }
         });

         requestNewInterstitial();

         View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView adView = (AdView) root.findViewById(R.id.adView);

        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (publisherInterstitialAd.isLoaded()) {
                    Log.i(LOG_TAG, "onClick: interstitial was ready");
                    publisherInterstitialAd.show();
                } else {
                    Log.i(LOG_TAG, "onClick: interstitial was not ready.");
                    progressBar.setVisibility(View.VISIBLE);
                    getJoke();
                }
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        return root;
    }

    public void getJoke() {
        new EndpointAsyncTask(getContext()).execute();
    }

    public void launchDisplayJokeActivity() {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(getContext(), JokeActivity.class);
            intent.putExtra(this.getString(R.string.jokeEnvelope), loadedJoke);

            context.startActivity(intent); // abd right after that triccer this will start another activity, remove one of them
            progressBar.setVisibility(View.GONE);
        }
    }

    private void requestNewInterstitial(){
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("EA27D37DF5448BF42AA5F7A6D4F11A9B")
                .build();
        publisherInterstitialAd.loadAd(adRequest);
    }

}

