package com.example.parrot.pong1;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //USING HAMCREST LIBRARY FOR COMPARISION FUNCTIONS

    //COLLISION DETECTION TESTS
    //SCENE 1
    //Testing that BottomLeft point of Ball should be greater than TopLeft point of Racket
    //Testing that BottomRight point of Ball should be less than TopRight point of Racket
    @Test
    public void checkCollisionSceneOne() {
        Ball ball = new Ball(700, 1400);
        Racket racket = new Racket(600, 1450);
        assertThat("SCENE-1 TEST-1",
                (long) (ball.getBallXPos()), greaterThan((long) racket.getRacketXPos()));

        assertThat("SCENE-1 TEST-2",
                (long) (racket.getRacketXPos() + 400)
                , greaterThan((long) (ball.getBallXPos() + 50)));

        //Check whether the ball and racket are actually touching each other or not
        assertEquals((long) ball.getBallYPos() + 50, (long) racket.getRacketYPos());

        // If all tests pass then ball is hitting somewhere inside the racket
    }

    //SCENE 2
    //Testing that BottomLeft point of Ball will be less than TopLeft point of Racket
    //        and
    // BottomRight point of Ball will be greater than TopLeft point of Racket
    @Test
    public void checkCollisionSceneTwo() {
        Ball ball = new Ball(575, 1400);
        Racket racket = new Racket(600, 1450);
        assertThat("SCENE-2 TEST-1",
                (long) (ball.getBallXPos()), lessThan((long) racket.getRacketXPos()));

        assertThat("SCENE-2 TEST-2",
                (long) (ball.getBallXPos() + 50), greaterThan((long) racket.getRacketXPos()));

        //Check whether the ball and racket are actually touching each other or not
        assertEquals((long) ball.getBallYPos() + 50, (long) racket.getRacketYPos());

        //If all of these Tests are passing then it means that Ball is colliding on left side of the racket
    }

    //SCENE 3
    //Testing that BottomLeft point of Ball will be less than TopRight point of Racket
    //        and
    // BottomRight point of Ball will be greater than TopRight point of Racket
    @Test
    public void checkCollisionSceneThree() {
        Ball ball = new Ball(975, 1400);
        Racket racket = new Racket(600, 1450);
        assertThat("SCENE-3 TEST-1",
                (long) (ball.getBallXPos()), lessThan((long) (racket.getRacketXPos() + 400)));

        assertThat("SCENE-3 TEST-2",
                (long) (ball.getBallYPos() + 50), greaterThan((long) (racket.getRacketXPos() + 400)));

        //Check whether the ball and racket are actually touching each other or not
        assertEquals((long) ball.getBallYPos() + 50, (long) racket.getRacketYPos());

        //If these all the Tests are passing then it means that Ball is colliding on right side of the racket
    }

    //Scene 4
    //Testing that BottomLeft point of Ball will be less than TopRight point of Racket
    //        and
    // BottomRight point of Ball will be less than TopRight point of Racket
    @Test
    public void checkCollisionSceneFour() {
        Ball ball = new Ball(450, 1400);
        Racket racket = new Racket(600, 1450);
        assertThat("SCENE-4 TEST-1",
                (long) (ball.getBallXPos()), lessThan((long) (racket.getRacketXPos())));

        assertThat("SCENE-4 TEST-2",
                (long) (ball.getBallXPos() + 50), lessThan((long) (racket.getRacketXPos())));

        //Check whether the ball and racket are actually touching each other or not
        assertEquals((long) ball.getBallYPos() + 50, (long) racket.getRacketYPos());

        //If these all the Tests are passing then it means that Ball is on the LEFT SIDE of Racket means NO COLLISION!
    }

    //Scene 5
    //Testing that BottomLeft point of Ball will be less than TopRight point of Racket
    //        and
    // BottomRight point of Ball will be less than TopRight point of Racket
    @Test
    public void checkCollisionSceneFive() {
        Ball ball = new Ball(1100, 1400);
        Racket racket = new Racket(600, 1450);
        assertThat("SCENE-4 TEST-1",
                (long) (ball.getBallXPos()), greaterThan((long) (racket.getRacketXPos())+400));

        assertThat("SCENE-4 TEST-2",
                (long) (ball.getBallXPos() + 50), greaterThan((long) (racket.getRacketXPos())+400));

        //Check whether the ball and racket are actually touching each other or not
        assertEquals((long) ball.getBallYPos() + 50, (long) racket.getRacketYPos());

        //If these all the Tests are passing then it means that Ball is on the LEFT SIDE of Racket means NO COLLISION!
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

}