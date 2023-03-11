package com.mygdx.firebase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Helicopter {
    private Texture img;
    private String[] images = {"heli1.png", "heli2.png", "heli3.png", "heli4.png"};
    private int animationImageIndex = 0;
    private float timeBetweenImages = 0.1f;
    private float animationProgress = 0f;
    private float xPos;
    private float yPos;
    private float xSpeed;
    private float ySpeed;
    private float timeAlive = 0f;
    private boolean isAlive = true;

    public Helicopter(float x, float y, float xSpeed, float ySpeed) {
        this.xPos = x;
        this.yPos = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        img = new Texture(images[animationImageIndex]);
    }

    public void render(SpriteBatch batch) {
        if (!isAlive) return;
        timeAlive += Gdx.graphics.getDeltaTime();
        animate();
        move();
        batch.draw(img, xPos, yPos);
        if (isClicked()) {
            die();
        }
    }

    public void dispose() {
        img.dispose();
    }

    private void animate() {
        animationProgress += Gdx.graphics.getDeltaTime();
        if (animationProgress > timeBetweenImages) {
            animationProgress = 0f;
            animationImageIndex = (animationImageIndex + 1) % images.length;
            img = new Texture(images[animationImageIndex]);
        }
    }

    private void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        xPos += xSpeed * deltaTime;
        yPos += ySpeed * deltaTime;
        if (xPos + img.getWidth() > Gdx.graphics.getWidth()) {
            xSpeed = Math.abs(xSpeed) * -1;
        }
        if (xPos < 0) {
            xSpeed = Math.abs(xSpeed);
        }
        if (yPos + img.getHeight() > Gdx.graphics.getHeight()) {
            ySpeed = Math.abs(ySpeed) * -1;
        }
        if (yPos < 0) {
            ySpeed = Math.abs(ySpeed);
        }
    }

    private boolean isClicked() {
        if (!Gdx.input.isTouched()) return false;
        int clickX = Gdx.input.getX();
        int clickY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if (clickX < xPos || clickX > xPos + img.getWidth()) return false;
        if (clickY < yPos || clickY > yPos + img.getHeight()) return false;
        return true;
    }

    private void die() {
        // default score added is 100, plus a time bonus if heli is clicked before 5 seconds
        // the max time bonus is 500, and decreases linearly to 0 (100 per second alive)
        isAlive = false;
        int defaultScore = 100;
        int timeScore = Math.round((5 - timeAlive) * 100);
        ScoreManager.instance.score += defaultScore + timeScore;
    }
}
