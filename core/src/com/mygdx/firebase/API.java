package com.mygdx.firebase;

import java.util.ArrayList;

public interface API {
    void submitHighscore(Score score);
    void getHighscores(ArrayList<Score> dataHolder);
}
