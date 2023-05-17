package com.example.tehem.blockbreaker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.lang.reflect.Field;

public class NamePicker extends Activity {

    Button Submit;
    Button Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_picker);

        MainActivity.MP.release();


        Submit = (Button)findViewById(R.id.Submit);

        Return = (Button)findViewById(R.id.Back);

        final PlayerActions PA = new PlayerActions(this);

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};


        final NumberPicker NumberPicker1 = (NumberPicker) findViewById(R.id.numberPicker1);
        NumberPicker1.setMinValue(0);
        NumberPicker1.setMaxValue(25);
        NumberPicker1.setDisplayedValues(alphabet);

        final NumberPicker NumberPicker2 = (NumberPicker) findViewById(R.id.numberPicker2);
        NumberPicker2.setMinValue(0);
        NumberPicker2.setMaxValue(25);
        NumberPicker2.setDisplayedValues(alphabet);

        final NumberPicker NumberPicker3 = (NumberPicker) findViewById(R.id.numberPicker3);
        NumberPicker3.setMinValue(0);
        NumberPicker3.setMaxValue(25);
        NumberPicker3.setDisplayedValues(alphabet);



        Intent intent = this.getIntent();
        final int score = intent.getIntExtra("myScore", -1);
        final Intent In = new Intent(this,LeaderBoard.class);
        final Intent GoBack = new Intent(this, MainActivity.class);






        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.MP.release();

                PA.Open_Create_DataBase();

                MyPlayers player = new MyPlayers();

                String nick = "";

                switch (NumberPicker1.getValue())
                {
                    case 0:
                        nick = nick + "A";
                        break;

                    case 1:
                        nick = nick + "B";
                        break;
                    case 2:
                        nick = nick + "C";
                        break;
                    case 3:
                        nick = nick + "D";
                        break;

                    case 4:
                        nick = nick + "E";
                        break;
                    case 5:
                        nick = nick + "F";
                        break;
                    case 6:
                        nick = nick + "G";
                        break;

                    case 7:
                        nick = nick + "H";
                        break;
                    case 8:
                        nick = nick + "I";
                        break;
                    case 9:
                        nick = nick + "J";
                        break;

                    case 10:
                        nick = nick + "K";
                        break;
                    case 11:
                        nick = nick + "L";
                        break;

                    case 12:
                        nick = nick + "M";
                        break;

                    case 13:
                        nick = nick + "N";
                        break;
                    case 14:
                        nick = nick + "O";
                        break;
                    case 15:
                        nick = nick + "P";
                        break;

                    case 16:
                        nick = nick + "Q";
                        break;
                    case 17:
                        nick = nick + "R";
                        break;
                    case 18:
                        nick = nick + "S";
                        break;

                    case 19:
                        nick = nick + "T";
                        break;
                    case 20:
                        nick = nick + "U";
                        break;
                    case 21:
                        nick = nick + "V";
                        break;

                    case 22:
                        nick = nick + "W";
                        break;
                    case 23:
                        nick = nick + "X";
                        break;
                    case 24:
                        nick = nick + "Y";
                        break;
                    case 25:
                        nick = nick + "Z";
                        break;
                }

                switch (NumberPicker2.getValue())
                {
                    case 0:
                        nick = nick + "A";
                        break;

                    case 1:
                        nick = nick + "B";
                        break;
                    case 2:
                        nick = nick + "C";
                        break;
                    case 3:
                        nick = nick + "D";
                        break;

                    case 4:
                        nick = nick + "E";
                        break;
                    case 5:
                        nick = nick + "F";
                        break;
                    case 6:
                        nick = nick + "G";
                        break;

                    case 7:
                        nick = nick + "H";
                        break;
                    case 8:
                        nick = nick + "I";
                        break;
                    case 9:
                        nick = nick + "J";
                        break;

                    case 10:
                        nick = nick + "K";
                        break;
                    case 11:
                        nick = nick + "L";
                        break;

                    case 12:
                        nick = nick + "M";
                        break;

                    case 13:
                        nick = nick + "N";
                        break;
                    case 14:
                        nick = nick + "O";
                        break;
                    case 15:
                        nick = nick + "P";
                        break;

                    case 16:
                        nick = nick + "Q";
                        break;
                    case 17:
                        nick = nick + "R";
                        break;
                    case 18:
                        nick = nick + "S";
                        break;

                    case 19:
                        nick = nick + "T";
                        break;
                    case 20:
                        nick = nick + "U";
                        break;
                    case 21:
                        nick = nick + "V";
                        break;

                    case 22:
                        nick = nick + "W";
                        break;
                    case 23:
                        nick = nick + "X";
                        break;
                    case 24:
                        nick = nick + "Y";
                        break;
                    case 25:
                        nick = nick + "Z";
                        break;
                }


                switch (NumberPicker3.getValue())
                {
                    case 0:
                        nick = nick + "A";
                        break;

                    case 1:
                        nick = nick + "B";
                        break;
                    case 2:
                        nick = nick + "C";
                        break;
                    case 3:
                        nick = nick + "D";
                        break;

                    case 4:
                        nick = nick + "E";
                        break;
                    case 5:
                        nick = nick + "F";
                        break;
                    case 6:
                        nick = nick + "G";
                        break;

                    case 7:
                        nick = nick + "H";
                        break;
                    case 8:
                        nick = nick + "I";
                        break;
                    case 9:
                        nick = nick + "J";
                        break;

                    case 10:
                        nick = nick + "K";
                        break;
                    case 11:
                        nick = nick + "L";
                        break;

                    case 12:
                        nick = nick + "M";
                        break;

                    case 13:
                        nick = nick + "N";
                        break;
                    case 14:
                        nick = nick + "O";
                        break;
                    case 15:
                        nick = nick + "P";
                        break;

                    case 16:
                        nick = nick + "Q";
                        break;
                    case 17:
                        nick = nick + "R";
                        break;
                    case 18:
                        nick = nick + "S";
                        break;

                    case 19:
                        nick = nick + "T";
                        break;
                    case 20:
                        nick = nick + "U";
                        break;
                    case 21:
                        nick = nick + "V";
                        break;

                    case 22:
                        nick = nick + "W";
                        break;
                    case 23:
                        nick = nick + "X";
                        break;
                    case 24:
                        nick = nick + "Y";
                        break;
                    case 25:
                        nick = nick + "Z";
                        break;
                }

                player.setName(nick);
                player.setScore(score);

                PA.Add_New_Player(player);
                startActivity(In);
                finish();

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.MP.release();
                startActivity(GoBack);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Sorry, please use the Restart/Submit button to proceed",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
