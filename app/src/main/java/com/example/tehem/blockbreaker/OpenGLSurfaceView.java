package com.example.tehem.blockbreaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Orerez on 28/11/2015.
 */
public class OpenGLSurfaceView extends GLSurfaceView {

    private final OpenGLRenderer Renderer;
    private float prevX;
    private boolean first = true;
    private VelocityTracker vTracker = null;
    MainActivity mainActivity;






    public OpenGLSurfaceView(Context activity, SubmitAction submit) {


        super(activity);

        Renderer = new OpenGLRenderer(submit);

        setRenderer(Renderer);


        if(first)
        {

            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            requestRender();
            first = false;

        }
        else {
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);


        }



    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {



        float x = event.getX();
        if(Renderer.missed)
        {


            Toast.makeText(getContext(),"Your Score is "+ Renderer.score,Toast.LENGTH_LONG).show();
            Renderer.score=0;
            Renderer.countHits=0;
            Renderer.countTime=0;
            Renderer.countBalls=1;
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            Renderer.missed = false;
            requestRender();
        }

        else
        {
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        }






        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(vTracker == null) {
                    vTracker = VelocityTracker.obtain();
                }
                else {
                    vTracker.clear();
                }
                vTracker.addMovement(event);
                break;

            case MotionEvent.ACTION_MOVE:
                vTracker.addMovement(event);
                vTracker.computeCurrentVelocity(1);
                Renderer.v = vTracker.getXVelocity();

                float dx = 1;


                dx = -(x - prevX);



                float locationX = Renderer.getX() + (float) (dx * 0.005);


                if ((locationX >= (-1.42)) && (locationX <= 1.42)) {




                    Renderer.SetX(locationX);



                }
                break;
            case MotionEvent.ACTION_CANCEL:
                vTracker.recycle();
                break;


        }




        prevX = x;




        return true;


    }




}

