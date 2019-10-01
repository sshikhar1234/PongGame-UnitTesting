package com.example.parrot.pong1;


public class Racket {
    int racketXPos;
    int racketYPos;

    public Racket(int racketXPos, int racketYPos) {
        this.racketXPos = racketXPos;
        this.racketYPos = racketYPos;
    }

    public void drawRacket() {

    }

    public int getRacketXPos() {
        return racketXPos;
    }

    public void setRacketXPos(int racketXPos) {
        this.racketXPos = racketXPos;
    }

    public int getRacketYPos() {
        return racketYPos;
    }

    public void setRacketYPos(int racketYPos) {
        this.racketYPos = racketYPos;
    }

}
