package com.mygdx.firebase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collections;

public class Highscore {
    private ArrayList<Score> highscores = new ArrayList<>();
    private BitmapFont font;

    public Highscore() {
        font = new BitmapFont();
        font.getData().setScale(2f);
        highscores = fetchHighscores();
        Collections.sort(highscores);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < highscores.size(); i++) {
            int xPos = Gdx.graphics.getWidth() / 2 - 30;
            int yPos = Gdx.graphics.getHeight() - 100 - (i * 50);
            String text = (i + 1) + ". " + highscores.get(i).toString();
            font.draw(batch, text, xPos, yPos);
        }
    }

    // TODO: Implement this
    private ArrayList<Score> fetchHighscores() {
        System.out.println("Fetching highscores");
        ArrayList<Score> newHighscores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newHighscores.add(new Score(0, "AAA"));
        }
        return newHighscores;
    }

    // TODO: Implement this
    public void submitHighscore(String name, int score) {
        System.out.println("Submitting highscore");
    }

}