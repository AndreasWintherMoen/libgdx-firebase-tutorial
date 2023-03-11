package com.mygdx.firebase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class FirebaseTutorial extends ApplicationAdapter {
	private SpriteBatch batch;
	private ArrayList<Helicopter> helicopters;
	private final int initialHeliCount = 5;
	private final float timeBetweenSpawns = 1f;
	private float spawnProgress = 0f;
	private BitmapFont scoreFont;
	private BitmapFont timerFont;
	private Highscore highscore;
	private boolean showEndScreen = false;

	API api;

	public FirebaseTutorial(API api) {
		this.api = api;
	}

	@Override
	public void create () {
		new ScoreManager();
		new TimerManager(10f);
		highscore = new Highscore(api);
		batch = new SpriteBatch();
		scoreFont = new BitmapFont();
		timerFont = new BitmapFont();
		scoreFont.getData().setScale(4f);
		timerFont.getData().setScale(4f);
		helicopters = new ArrayList<>();
		for (int i = 0; i < initialHeliCount; i++) {
			helicopters.add(initHeli());
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		if (showEndScreen) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				resetLevel();
			}
			highscore.render(batch);
			scoreFont.draw(batch, "Score: " + ScoreManager.instance.score, 10, Gdx.graphics.getHeight() - 10);
			timerFont.draw(batch, "Time: 0.00", 10, Gdx.graphics.getHeight() - 100);
			batch.end();
			return;
		}

		TimerManager.instance.update();
		if (TimerManager.instance.time <= 0) {
			endLevel();
		}

		for (Helicopter helicopter : helicopters) {
			helicopter.render(batch);
		}
		handleSpawningNewHelis();
		scoreFont.draw(batch, "Score: " + ScoreManager.instance.score, 10, Gdx.graphics.getHeight() - 10);
		timerFont.draw(batch, "Time: " + String.format("%.2f", TimerManager.instance.time), 10, Gdx.graphics.getHeight() - 100);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Helicopter helicopter : helicopters) {
			helicopter.dispose();
		}
		scoreFont.dispose();
	}

	private void endLevel() {
		showEndScreen = true;

		Input.TextInputListener listener = new Input.TextInputListener() {
			@Override
			public void input(String text) {
				highscore.submitHighscore(text, ScoreManager.instance.score);
			}
			@Override
			public void canceled() {}
		};

		Gdx.input.getTextInput(listener, "Enter your name", "", "Name...");
	}

	private void resetLevel() {
		showEndScreen = false;
		TimerManager.instance.Reset(10f);
		ScoreManager.instance.score = 0;
		helicopters.clear();
		for (int i = 0; i < initialHeliCount; i++) {
			helicopters.add(initHeli());
		}
	}

	private void handleSpawningNewHelis() {
		spawnProgress += Gdx.graphics.getDeltaTime();
		if (spawnProgress > timeBetweenSpawns) {
			spawnProgress = 0f;
			helicopters.add(initHeli());
		}
	}

	private Helicopter initHeli() {
		float x = (float) Math.random() * Gdx.graphics.getWidth();
		float y = (float) Math.random() * Gdx.graphics.getHeight();
		float xSpeed = (float) Math.random() * 100 + 100;
		float ySpeed = (float) Math.random() * 100 + 100;
		return new Helicopter(x, y, xSpeed, ySpeed);
	}
}
