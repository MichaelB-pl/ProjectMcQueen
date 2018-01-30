package com.example.micha.projectmcqueen.models;

import android.net.Uri;

/**
 * Created by micha on 03.10.2017.
 */

public class AlphabethItem {
    public final String letter;

    public final String firstImageName;
    public final String secondImageName;
    public final String thirdImageName;
    public final String fourthImageName;
    public final String fifthImageName;

    public final int firstImageResId;
    public final int secondImageResId;
    public final int thirdImageResId;
    public final int fourthImageResId;
    public final int fifthImageResId;

    public AlphabethItem(String letter,
                         String firstImageName, String secondImageName, String thirdImageName, String fourthImageName, String fifthImageName,
                         int firstImageResId, int secondImageResId, int thirdImageResId, int fourthImageResId, int fifthImageResId) {
        this.letter = letter;

        this.firstImageName = firstImageName;
        this.secondImageName = secondImageName;
        this.thirdImageName = thirdImageName;
        this.fourthImageName = fourthImageName;
        this.fifthImageName = fifthImageName;

        this.firstImageResId = firstImageResId;
        this.secondImageResId = secondImageResId;
        this.thirdImageResId = thirdImageResId;
        this.fourthImageResId = fourthImageResId;
        this.fifthImageResId = fifthImageResId;
    }

    private String getLetterUriFolder() {
        String uri = "asset:///letters/" + letter.toLowerCase() + "/";
        return uri;
    }

    public String getLetterAudioUri() {
        String uri = getLetterUriFolder() + letter.toLowerCase() + ".mp3";
        return uri;
    }
}
