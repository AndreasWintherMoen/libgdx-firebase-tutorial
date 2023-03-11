package com.mygdx.firebase;

import com.badlogic.gdx.Gdx;

public class TimerManager {
    public static TimerManager instance;
    public float time;

    public TimerManager(float time) {
        if (instance == null) {
            instance = this;
        }
        else {
            throw new RuntimeException("TimerManager singleton already exists");
        }

        this.time = time;
    }

    public void Reset(float time) {
        this.time = time;
    }

    public void update() {
        time -= Gdx.graphics.getDeltaTime();
    }
}
