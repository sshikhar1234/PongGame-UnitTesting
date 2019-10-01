package com.example.parrot.pong1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameEngine extends SurfaceView implements Runnable {

    final static String TAG="PONG-GAME";
    int screenHeight;
    int screenWidth;
    boolean gameIsRunning;

    Thread gameThread;

    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;

    Ball ball;
    Racket racket;
    Stats stats;

    public GameEngine(Context context, int w, int h) {
        super(context);
        this.holder = this.getHolder();
        this.paintbrush = new Paint();
        this.screenWidth = w;
        this.screenHeight = h;
        this.printScreenInfo();
        stats = new Stats(5,0);

        //Initialize the Game Ball Object

        this.ball = new Ball(this.screenWidth/2,(this.screenHeight/2)-400);
        this.racket = new Racket(350,1200);
    }

    // ------------------------------
    // HELPER FUNCTIONS
    // ------------------------------

    // This function prints the screen height & width to the screen.
    private void printScreenInfo() {
        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    @Override
    public void run() {
        while (gameIsRunning) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------


    String directionBallIsMoving = "down";
    String personTapped="";

    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {
        // @TODO: Update the position of the sprites

        Log.d(TAG, "updatePositions: Ball X "+ball.getBallXPos());
        Log.d(TAG, "updatePositions: Ball Y "+ball.getBallYPos());
        if (directionBallIsMoving .equals("down")) {
            this.ball.setBallYPos(this.ball.getBallYPos()+25);

            // if ball hits the floor, then change its direction
            if (this.ball.getBallYPos() >= this.screenHeight) {
                // Restart the game
                // Put everything back in their default positions
                // --------------------
                // restart the ball
                this.ball.setBallXPos(this.screenWidth / 2);
                this.ball.setBallYPos((this.screenHeight / 2) - 400);

                this.racket.setRacketXPos(350);
                this.racket.setRacketYPos(1200);

                // restart hte direction
                directionBallIsMoving = "down";

                // clear any previous user actions
                personTapped = "";
                stats.setLives(stats.getLives()-1);
            }
        }
        if (directionBallIsMoving .equals("up")) {
            this.ball.setBallYPos(this.ball.getBallYPos()-25);

            // if ball hits ceiling, then change directions
            if(this.ball.getBallYPos()<=0){
                // hit upper wall
                directionBallIsMoving = "down";
            }
        }

        // calculate the racket's new position
        if (personTapped.contentEquals("right")){
            this.racket.setRacketXPos( this.racket.getRacketXPos() + 10);
        }
        else if (personTapped.contentEquals("left")){
            this.racket.setRacketXPos(this.racket.getRacketXPos() - 10);
        }


        // @TODO: Collision detection code

        // detect when ball hits the racket
        // ---------------------------------

        // 1. if ball hits racket, bounce off racket

        // Check that ball is inside the x and y boundaries
        // of the racket

        // BallY + 50 because we want to make a more precise collision
        // When bottom left corner of ball touches racket, then bounce!
        // (ballY+50) = bottom left
        if ((this.ball.getBallYPos() + 50) >= (this.racket.getRacketYPos()) &&
                this.ball.getBallXPos() >= this.racket.getRacketXPos() &&
                this.ball.getBallXPos() <= this.racket.getRacketXPos() + 400) {

            // ball is touching racket
            Log.d(TAG, "Ball IS TOUCHING RACKET!");
            directionBallIsMoving = "up";

            // increase the game score!
            stats.setScore(stats.getScore()+50);
//            this.score = this.score + 50;
        }
        // 2. if ball misses racket, then keep going down

        // 3. if ball falls off bottom of screen, restart the ball in middle
    }

    // 2. Tell Android to DRAW the sprites at their positions
    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------
            // Put all your drawing code in this section

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,0,255));
            paintbrush.setColor(Color.WHITE);

            //@TODO: Draw the sprites (rectangle, circle, etc)

            // 1. Draw the ball
            this.canvas.drawRect(
                    ball.getBallXPos(),
                    ball.getBallYPos(),
                    ball.getBallXPos() + 50,
                    ball.getBallYPos() + 50,
                    paintbrush);

            // 2. Draw the racket

            paintbrush.setColor(Color.YELLOW);
            this.canvas.drawRect(this.racket.getRacketXPos(),
                    this.racket.getRacketYPos(),
                    this.racket.getRacketXPos() + 400,     // 400 is width of racket
                    this.racket.getRacketYPos() + 50,    // 50 is height of racket
                    paintbrush);
            paintbrush.setColor(Color.WHITE);


            //@TODO: Draw game statistics (lives, score, etc)
            paintbrush.setTextSize(60);
            canvas.drawText("Score: " + stats.getScore(), 20, 100, paintbrush);

            canvas.drawText("Lives: " + stats.getLives(), 20, 160, paintbrush);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    // Sets the frame rate of the game
    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        // ---------------------------------------------------------
        // Get position of the tap
        // Compare position of tap to the middle of the screen
        // If tap is on left, move racket Position to left
        // If tap is on right, move racket position to right

        if (userAction == MotionEvent.ACTION_DOWN) {
            // user pushed down on screen

            // 1. Get position of tap
            float fingerXPosition = event.getX();
            float fingerYPosition = event.getY();
            Log.d(TAG, "Person's pressed: "
                    + fingerXPosition + ","
                    + fingerYPosition);

            // 2. Compare position of tap to middle of screen
            int middleOfScreen = this.screenWidth / 2;
            if (fingerXPosition <= middleOfScreen) {
                // 3. If tap is on left, racket should go left
                personTapped = "left";
            }
            else if (fingerXPosition > middleOfScreen) {
                // 4. If tap is on right, racket should go right
                personTapped = "right";
            }
        }
        else if (userAction == MotionEvent.ACTION_UP) {
            // user lifted their finger
        }
        return true;
    }
}