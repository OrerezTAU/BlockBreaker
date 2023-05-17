package com.example.tehem.blockbreaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements  GLSurfaceView.Renderer {

    public Rectangle Rect;
    FlatColoredSquare Square;
    Ball Ball1;
    Ball Ball2;
    Ball Ball3;
    Ball Ball4;
    private float x;
    public  float winwidth =-1;
    public float winheight=-1;
    private boolean bool = true; //above the bar
    public boolean missed = false;
    private boolean done;
    public float prevStop = 0.0f;
    public float Stop;
    public int countTime = 0;
    public int countHits = 0;
    public int countBalls = 1;
    public int score = 0;
    public float v;
    private float velocity = 1.2f;
    private boolean ballengaged = false;
    private boolean ballengaged2 = false;
    private boolean ballengaged3 = false;

    private ArrayList<Boolean> Directions = new ArrayList<>();
    private ArrayList<Float> Locations = new ArrayList<>();

    public Block[][] Blocks = new Block[3][6];
    public boolean[][] hit = new boolean[3][6];
    public int[][] count = new int[3][6];

    SubmitAction mSubmitAction;

    public OpenGLRenderer(SubmitAction submitAction) {
        super();
        mSubmitAction = submitAction;

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(0, 0, 0, 1);
        // Set the background color to black ( rgba )

        Rect = new Rectangle();
        Square = new FlatColoredSquare();
        Ball1 = new Ball();
        Ball2 = new Ball();
        Ball3 = new Ball();

        try {
            if(!MainActivity.MP.isPlaying()) {
                MainActivity.MP.start();
            }
        }

        catch (Exception e)
        {

        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 ; j++) {
                Blocks[i][j] = new Block();
                hit[i][j] = false;
                count[i][j] = 0;
            }
        }


        Directions.add(true);
        Directions.add(true);
        Locations.add(1.0f);
        Locations.add(1.0f);

        Directions.add(false);
        Directions.add(true);
        Locations.add(1.0f);
        Locations.add(1.0f);

        Directions.add(true);
        Directions.add(true);
        Locations.add(1.0f);
        Locations.add(-1.0f);

        Directions.add(true);
        Directions.add(true);
        Locations.add(1.0f);
        Locations.add(-1.0f);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {

        gl.glViewport(0, 0, width, height);
        // Sets the current view port to the new size.
        // (The viewport is the user's visible area of a web page.
        //The viewport varies with the device, and will be smaller on a mobile phone than on a computer screen.)

        winheight = height;
        winwidth = width;

       float ratio = (float)width/height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);

    }

    @Override
    public void onDrawFrame(GL10 gl)
    {

       gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
               GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        gl.glPushMatrix();

        gl.glTranslatef(0.0f, -0.7f, 0.0f);

        gl.glPushMatrix();

        gl.glTranslatef(x, 0.0f, 0.0f);

        Rect.draw(gl);

        gl.glPopMatrix();

        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glScalef(0.12f, 0.12f, 1.0f);

        countTime++;

        if(!missed) {

            gl.glPushMatrix();

            gl.glTranslatef(0.0f,-3.0f,0.0f);

            gl.glPushMatrix();

            gl.glTranslatef(Locations.get(0) / 15, Locations.get(1) / 15, 0.0f);
        }

        else
        {


            mSubmitAction.transfer(score);
           gl.glTranslatef(0.0f, -0.8f, 0.0f);

            for (int i = 0; i <Locations.size() ; i+=2)
            {
                Locations.set(i, 1.0f);
                Locations.set(i+1,-7.0f);
            }

            x=0.0f;
            velocity = 1.2f;
            bool = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 6 ; j++) {
                    Blocks[i][j] = new Block();
                    hit[i][j] = false;
                    count[i][j] = 0;
                }
            }

        }


        Ball1.draw(gl, 215, 0, 0, 1.0f);

        checkDir(Locations.get(0), Locations.get(1));

        move(0);

        checkCollision(Locations.get(0), Locations.get(1));




//-------------------------------------------------------

        gl.glPopMatrix();
        gl.glPopMatrix();
        gl.glPopMatrix();

        if(countTime > 200)
        {
            countBalls = 2;

            gl.glPushMatrix();

            gl.glScalef(0.12f, 0.12f, 1.0f);

            gl.glPushMatrix();

            gl.glTranslatef(0.0f, -3.0f, 0.0f);

            gl.glPushMatrix();

            gl.glTranslatef(Locations.get(2) / 15, Locations.get(3) / 15, 0.0f);

            Ball2.draw(gl, 0, 73, 255, 1.0f);

            if(ballengaged = false)
            {
                velocity = velocity /2;
                ballengaged = true;

            }


            checkDir(Locations.get(2), Locations.get(3));

            move(2);

            checkCollision(Locations.get(2), Locations.get(3));

            gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPopMatrix();
        }

        if(countTime > 600)
        {
            countBalls = 3;

            gl.glPushMatrix();

            gl.glScalef(0.12f, 0.12f, 1.0f);

            gl.glPushMatrix();

            gl.glTranslatef(0.0f, -3.0f, 0.0f);

            gl.glPushMatrix();

            gl.glTranslatef(Locations.get(4) / 15, Locations.get(5) / 15, 0.0f);

            Ball3.draw(gl, 255, 182, 0, 1.0f);

            if(ballengaged2 = false)
            {
                velocity = velocity /2;
                ballengaged2 = true;

            }


            checkDir(Locations.get(4), Locations.get(5));

            move(4);

            checkCollision(Locations.get(4), Locations.get(5));

            gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPopMatrix();
        }

        if(countTime > 1100)
        {
            countBalls = 4;

            gl.glPushMatrix();

            gl.glScalef(0.12f, 0.12f, 1.0f);

            gl.glPushMatrix();

            gl.glTranslatef(0.0f, -3.0f, 0.0f);

            gl.glPushMatrix();

            gl.glTranslatef(Locations.get(6) / 15, Locations.get(7) / 15, 0.0f);

            Ball3.draw(gl, 0, 255, 0, 1.0f);

            if(ballengaged3 = false)
            {
                velocity = velocity /2;
                ballengaged3 = true;

            }


            checkDir(Locations.get(6), Locations.get(7));

            move(6);

            checkCollision(Locations.get(6), Locations.get(7));

            gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPopMatrix();
        }



        bool = true;

        float n = 1.6f;
        float m = 0.85f;

        score = (countTime+3*countHits)*countBalls *100;
        done = true;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j <6 ; j++)
            {

                gl.glPushMatrix();
                gl.glTranslatef(n,m,0.0f);

                if(!hit[i][j]) {
                    Blocks[i][j].draw(gl);
                    done= false;
                }

                gl.glPopMatrix();
                n = n-0.65f;

            }

            n = 1.6f;
           m=m-0.35f;

        }
//---------------------------------------------finished---------------------------------------------------
        if (done == true)
        {
            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 6; j++) {
                    hit[i][j] = false;
                    count[i][j] = 0;
                    Locations.set(0,1.0f);
                    Locations.set(1,1.0f);
                    Directions.set(1,true);


                }
            }
        }


    }
public float getX()
    {
        return  x;
    }

    public void SetX(float newX)
    {
        x = newX;
    }


    public void firsthitting(int i, int j, float b)
    {
        hit[i][j] = true;
        count[i][j] = 1;
        for (int k = 1; k <Locations.size() ; k+=2)
        {

            if(Locations.get(k) == b)
            {
                Directions.set(k,false);
            }
        }

        countHits++;
        MainActivity.MP2.start();

    }


    public void checkDir(float x, float y)
    {
        if ( x>=225)
        {
            for (int k = 0; k <Locations.size() ; k+=2)
            {

                if(Locations.get(k) == x)
                {
                    Directions.set(k,false);
                }
            }
        }

        if ( y>=112.5)
        {
            for (int k = 1; k <Locations.size() ; k+=2)
            {

                if(Locations.get(k) == y)
                {
                    Directions.set(k,false);
                }
            }
        }

        if ( x<=-225)
        {
            for (int k = 0; k <Locations.size() ; k+=2)
            {

                if(Locations.get(k) == x)
                {
                    Directions.set(k,true);
                }
            }
        }

        if ( y<=-112.5)
        {
            for (int k = 1; k <Locations.size() ; k+=2)
            {

                if(Locations.get(k) == y)
                {
                    Directions.set(k,true);
                }
            }
        }
    }

    public void move(int i)
    {
        if ( Directions.get(i)== true)
        {
            Locations.set(i,Locations.get(i)+(2+velocity));
        }

        else
        {
            Locations.set(i,Locations.get(i)-(2+velocity));
        }

        if (  Directions.get(i+1) == true)
        {
            Locations.set(i+1,Locations.get(i+1)+(2+velocity));
        }

        else
        {
            Locations.set(i+1,Locations.get(i+1)-(2+velocity));
        }
    }

    public void checkCollision (float a, float b)
    {
        if((b<=-28) && (bool)) //hits the bar
        {

            if ( (a/200-0.4f<=x+0.5f) && (a/200+0.4f>=x-0.5f))
            {

                MainActivity.MP3.start();


                if (Stop-prevStop<0)
                {
                    Directions.set(0,false);
                }

                if (Stop-prevStop>0)
                {
                    Directions.set(0,true);
                }

                if((v>velocity) && (velocity<4))
                {
                    velocity++;
                }


                for (int k = 1; k <Locations.size() ; k+=2)
                {

                    if(Locations.get(k) == b)
                    {
                        Directions.set(k,true);
                    }
                }
                bool = false;
                prevStop = Stop;

            }

            else
            {
                missed = true;
            }

        }

        else if (b >= 110 && (b<=115)) //hits first row
        {
//-----------------------Block 0 --------------------------
            if ((a <= 225) && (a >= 150)) {

                if (count[0][0] == 1 && (b <= 75)) //hits from below
                {
                    //upY = true;

                } else if (count[0][0] == 1 && (b > 75)) //hits from above
                {

                     Directions.set(1,false);
                } else //first time hit
                {
                    if ((b>=113 && (b<=117)))
                    {
                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(0,0,b);
                }

            }
//-----------------------Block 1 --------------------------
            else if ((a <= 150) && (a >= 75)) {
                if (count[0][1] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[0][1] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }

                else
                {
                    if ((b>=113 && (b<=117)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(0,1,b);
                }


            }
//-----------------------Block 2 --------------------------
            else if ((a <= 75) && (a >= 0)) {
                if (count[0][2] == 1 && (b <= 75))
                {
                    //upY = true;

                }
                else if (count[0][2] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=113 && (b<=117)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(0,2,b);
                }
            }
//-----------------------Block 3 --------------------------
            else if ((a <= 0) && (a >= -75)) {
                if (count[0][3] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[0][3] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=113 && (b<=117)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(0,3,b);
                }
            }
//-----------------------Block 4 --------------------------
            else if ((a <= -75) && (a >= -150))
            {
                if (count[0][4] == 1 && (b <= 75))
                {
                    //upY = true;

                }

                else if (count[0][4] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=113 && (b<=117)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(0,4,b);
                }
            }
//-----------------------Block5 --------------------------
            else if ((a <= -150) && (a >= -225)) {
                if (count[0][5] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[0][5] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {

                        if ((b>=113 && (b<=117)))
                        {

                             Directions.set(0,!Directions.get(0));
                        }


                    firsthitting(0,5,b);
                }
            }
        }
//----------------------------------------------------------------------------------------------------------------------------
        else if ((b >= 68) && (b <= 73)) //hits row 2
        {
//-----------------------Block 0 --------------------------
            if ((a <= 225) && (a >= 150)) {

                if (count[1][0] == 1 && (b <= 75)) //hits from below
                {
                    //upY = true;

                }
                else if (count[1][0] == 1 && (b > 75)) //hits from above
                {

                     Directions.set(1,false);
                } else //first time hit
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,0,b);
                }

            }
//-----------------------Block 1 --------------------------
            else if ((a <= 150) && (a >= 75)) {
                if (count[1][1] == 1 && (b <= 75))
                {
                    //upY = true;

                }
                else if (count[1][1] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,1,b);
                }


            }
//-----------------------Block 2 --------------------------
            else if ((a <= 75) && (a >= 0))
            {
                if (count[1][2] == 1 && (b <= 75))
                {
                    //upY = true;

                } else if (count[1][2] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }

                else
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,2,b);
                }
            }
//-----------------------Block 3 --------------------------
            else if ((a <= 0) && (a >= -75))
            {
                if (count[1][3] == 1 && (b <= 75))
                {
                    //upY = true;

                } else if (count[1][3] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,3,b);
                }
            }
//-----------------------Block 4 --------------------------
            else if ((a <= -75) && (a >= -150))
            {
                if (count[1][4] == 1 && (b <= 75))
                {
                    //upY = true;

                } else if (count[1][4] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,4,b);
                }
            }
//-----------------------Block5 --------------------------
            else if ((a <= -150) && (a >= -225))
            {
                if (count[1][5] == 1 && (b <= 75))
                {
                    //upY = true;

                } else if (count[1][5] == 1 && (b > 75))
                {

                     Directions.set(1,false);
                }
                else
                {
                    if ((b>=71 && (b<=75)))
                    {

                         Directions.set(0,!Directions.get(0));
                    }
                    firsthitting(1,5,b);
                }
            }

        }

//-------------------------------------------------------
        else if ((b >= 27) && (b <= 32)) {//hits row 3
//-----------------------Block 0 --------------------------
            if ((a <= 225) && (a >= 150)) {

                if (count[2][0] == 1 && (b <= 75)) //hits from below
                {
                    //upY = true;

                } else if (count[2][0] == 1 && (b > 75)) //hits from above
                {

                    Directions.set(1, false);
                }
                else //first time hit
                {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 0,b);


                }

            }
//-----------------------Block 1 --------------------------
            else if ((a <= 150) && (a >= 75)) {
                if (count[2][1] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[2][1] == 1 && (b > 75)) {

                    Directions.set(1, false);
                } else {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 1,b);

                }


            }
//-----------------------Block 2 --------------------------
            else if ((a <= 75) && (a >= 0)) {
                if (count[2][2] == 1 && (b <= 75)) {
                    //upY = true;
                } else if (count[2][2] == 1 && (b > 75)) {

                    Directions.set(1, false);
                } else {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 2,b);

                }
            }
//-----------------------Block 3 --------------------------
            else if ((a <= 0) && (a >= -75)) {
                if (count[2][3] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[2][3] == 1 && (b > 75)) {

                    Directions.set(1, false);
                } else {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 3,b);

                }
            }
//-----------------------Block 4 --------------------------
            else if ((a <= -75) && (a >= -150)) {
                if (count[2][4] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[2][4] == 1 && (b > 75)) {

                    Directions.set(1, false);
                } else {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 4,b);

                }
            }
//-----------------------Block5 --------------------------
            else if ((a <= -150) && (a >= -225)) {
                if (count[2][5] == 1 && (b <= 75)) {
                    //upY = true;

                } else if (count[2][5] == 1 && (b > 75)) {

                    Directions.set(1, false);
                } else {
                    if ((b >= 30 && (b <= 34))) {

                        Directions.set(0, !Directions.get(0));
                    }
                    firsthitting(2, 5,b);

                }
            }
        }


    }

}
