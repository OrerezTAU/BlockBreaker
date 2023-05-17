package com.example.tehem.blockbreaker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements SubmitAction {

    private GLSurfaceView view;
    public static MediaPlayer MP;
    public static MediaPlayer MP2;
    public static MediaPlayer MP3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        view = new OpenGLSurfaceView(this, this);
        MP = MediaPlayer.create(this, R.raw.song1);
        MP2 = MediaPlayer.create(this,R.raw.blop);
        MP3 = MediaPlayer.create(this,R.raw.barhit);

        setContentView(view);

    }

    public void transfer(int score)
    {
        Intent I = new Intent(this,NamePicker.class);
        I.putExtra("myScore",score);
        startActivity(I);
    }



    @Override
    protected void onPause() {
        super.onPause();

        view.onPause();


    }



    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }


}
