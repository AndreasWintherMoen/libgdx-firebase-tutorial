package com.mygdx.firebase;

public class IosAPI implements API {
    @Override
    public void submitHighscore(Score score) {
        System.out.println("Ios submit highscore");
    }

    @Override
    public void getHighscores(ArrayList<Score> dataHolder) {}
}
