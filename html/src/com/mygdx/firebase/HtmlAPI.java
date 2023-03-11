package com.mygdx.firebase;

public class HtmlAPI implements API {
    @Override
    public void submitHighscore(Score score) {
        System.out.println("Html submit highscore");
    }

    @Override
    public void getHighscores(ArrayList<Score> dataHolder) {}
}
