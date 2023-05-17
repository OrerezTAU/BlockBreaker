package com.example.tehem.blockbreaker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OpenMenu extends Activity implements View.OnClickListener {

    Button SG;
    Button HTP;
    Button LB;
    Button Q;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);

        SG = (Button)findViewById(R.id.SG);
        SG.setOnClickListener(this);

        HTP = (Button)findViewById(R.id.HTP);
        HTP.setOnClickListener(this);


        LB = (Button)findViewById(R.id.LB);
        LB.setOnClickListener(this);


        Q = (Button)findViewById(R.id.Q);
        Q.setOnClickListener(this);



    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v)

    {
        Intent I;

       if (v.getId() == SG.getId())
       {
           I = new Intent(this,MainActivity.class);
           startActivity(I);
           finish();


       }

      if (v.getId() == HTP.getId())
        {
            I = new Intent(this,Instructions.class);
            startActivity(I);
            finish();

        }


       if (v.getId() == LB.getId())
        {
            I = new Intent(this,LeaderBoard.class);
            startActivity(I);
            finish();

        }

       if (v.getId() == Q.getId())
       {

           this.finishAffinity();

       }



    }



}
