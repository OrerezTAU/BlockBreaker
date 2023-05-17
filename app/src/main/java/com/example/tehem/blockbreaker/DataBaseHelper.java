package com.example.tehem.blockbreaker;

/**
 * Created by tehem on 28/02/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;






    public class DataBaseHelper extends SQLiteOpenHelper
    {
        //DataBase Name
        public static final String DATABASE_NAME="ScoreBoard.db";

        //DataBase Version
        public static final int DATABASE_VERSION=2;

        // Table 's Name
        public static final String BOARD="ScoreB";

        //Fields
        public static final String PLAYER_ID="ids";

        public static final String PLAYER_NAME="names";

        public static final String PLAYER_SCORE="scores";


        //Sql Statement

        public DataBaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String sql_statement="Create table " + BOARD

                    + "(" + PLAYER_ID + " integer primary key autoincrement,"

                    + PLAYER_NAME + " text not null,"

                    + PLAYER_SCORE + " integer);";

            db.execSQL(sql_statement);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + BOARD);

            // Create tables again
            onCreate(db);
        }
    }


