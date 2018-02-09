package com.example.micha.projectmcqueen.models;

import android.net.Uri;

import com.example.micha.projectmcqueen.R;

import java.util.ArrayList;
import java.util.List;

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

    /*private String getLetterUriFolder() {
        return getLetterUriFolder(this.letter);
    }*/

    private static String getLetterUriFolder(String letter) {
        String uri = "asset:///letters/" + letter.toLowerCase() + "/";
        return uri;
    }

    public String getLetterAudioUri() {
        return getLetterAudioUri(this.letter);
    }

    private static String getLetterAudioUri(String letter) {
        String uri = getLetterUriFolder(letter) + letter.toLowerCase() + ".mp3";
        return uri;
    }

    public String getItemAudioUri(int index) {
        String itemName;
        switch (index) {
            case 0:
                itemName = firstImageName.toLowerCase();
                break;
            case 1:
                itemName = secondImageName.toLowerCase();
                break;
            case 2:
                itemName = thirdImageName.toLowerCase();
                break;
            case 3:
                itemName = fourthImageName.toLowerCase();
                break;
            case 4:
                itemName = fifthImageName.toLowerCase();
                break;
            default:
                return "";
        }
        String uri = getLetterUriFolder(this.letter) + itemName + ".mp3";
        return uri;
    }

    public List<String> getItemLettersAudioUri(int index) {
        List<String> uris = new ArrayList<>();
        String itemName;
        switch (index) {
            case 0:
                itemName = firstImageName.toLowerCase();
                break;
            case 1:
                itemName = secondImageName.toLowerCase();
                break;
            case 2:
                itemName = thirdImageName.toLowerCase();
                break;
            case 3:
                itemName = fourthImageName.toLowerCase();
                break;
            case 4:
                itemName = fifthImageName.toLowerCase();
                break;
            default:
                return uris;
        }
        char[] letters = itemName.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            String letter = Character.toString(letters[i]);
            String uri = getLetterAudioUri(letter);
            uris.add(uri);
        }
        return uris;
    }

    public boolean isItemEmpty(int index) {
        switch (index) {
            case 0:
                return isItemEmpty(firstImageName, firstImageResId);
            case 1:
                return isItemEmpty(secondImageName, secondImageResId);
            case 2:
                return isItemEmpty(thirdImageName, thirdImageResId);
            case 3:
                return isItemEmpty(fourthImageName, fourthImageResId);
            case 4:
                return isItemEmpty(fifthImageName, fifthImageResId);
            default:
                return true;
        }
    }

    private boolean isItemEmpty(String imageName, int imageId) {
        return imageName.equals("") || imageId == R.drawable.transparent_rectangle;
    }
}
