package com.mygdx.firebase;

public class DesktopAPI implements API {
    @Override
    public void submitHighscore(Score score) {
        System.out.println("Desktop submit highscore");
    }

    @Override
    public void getHighscores(ArrayList<Score> dataHolder) {}
}
