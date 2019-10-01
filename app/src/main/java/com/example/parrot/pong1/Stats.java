package com.example.parrot.pong1;

public class Stats {
    int lives;
    int score;

    public Stats(int lives, int score) {
        this.lives = lives;
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
