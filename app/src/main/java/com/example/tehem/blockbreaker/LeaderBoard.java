package com.example.tehem.blockbreaker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LeaderBoard extends ActionBarActivity {


    ListView L1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);


        final PlayerActions PA = new PlayerActions(this);
        PA.Open_Create_DataBase();


        List<MyPlayers>PlayerList = PA.GetAllPlayers();



        final List<String>Ls=new ArrayList<String>();
        final List<String>Top10=new ArrayList<String>();


        for(MyPlayers x : PlayerList)
        {
            Ls.add(x.getName() + " " + String.valueOf(x.getScore()));

        }

        L1 = (ListView)findViewById(R.id.listView);
        Collections.sort(Ls, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                int num1 = Integer.parseInt(lhs.split(" ")[1]);
                int num2 = Integer.parseInt(rhs.split(" ")[1]);

                return num2 - num1;
            }


        });

       int n = Ls.size();

        if (n<=10)
        {
            ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,R.layout.list_black_background,R.id.list_content,Ls);

            L1.setAdapter(myadapter);
        }

        else
        {
            for (int i = 0; i < 10; i++)
            {
                Top10.add(i,Ls.get(i));
            }

            ArrayAdapter<String> myadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Top10);

            L1.setAdapter(myadapter);
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leader_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            if( MainActivity.MP.isPlaying()) {
                MainActivity.MP.release();
            }
            Intent I = new Intent(this,MainActivity.class);
            startActivity(I);
            finish();
        }

        if (id == R.id.Back)
        {

            Intent I = new Intent(this,OpenMenu.class);
            startActivity(I);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
