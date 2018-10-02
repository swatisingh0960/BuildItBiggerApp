package com.udacity.gradle.builditbigger.backend;

import com.nanodegree.swatisingh.javajoker.Joker;

/** The object model for the data we are sending through endpoints */
public class MyBean{

    private String joker;

    public void setJoker(String joker) {
        this.joker = joker;
    }
    public String getJoke() {
     return joker;
    }

}