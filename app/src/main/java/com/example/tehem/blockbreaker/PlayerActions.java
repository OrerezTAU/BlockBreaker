package com.example.tehem.blockbreaker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tehem on 28/02/2016.
 */
public class PlayerActions
{


        private DataBaseHelper dbhelper; // DataBaseHelper variable

        private SQLiteDatabase db; // SQLiteDatabase variable

        private String[] ContactTable_Fields={DataBaseHelper.PLAYER_ID,DataBaseHelper.PLAYER_NAME,

                DataBaseHelper.PLAYER_SCORE};
        //------------------------------------
        public PlayerActions(Context ctx)
        {
            this.dbhelper= new DataBaseHelper(ctx); // DataBaseHelper constructor, defines databse
        }
        //---------------------------------------
        public void Open_Create_DataBase()
        {
            // this code opens or creates  a database and calls OnCreate in DataBaseHelper class
            db=this.dbhelper.getReadableDatabase();
        }
        //----------------------------------------
        public MyPlayers SearchPlayer(String name,int score)
        {
            // db = dbhelper.getReadableDatabase();

            String sql="Select * from " + this.dbhelper.BOARD + " Where " +

                    this.dbhelper.PLAYER_NAME + "='" + name + "' and " +

                    this.dbhelper.PLAYER_SCORE +

                    "='" + score + "';";

            Cursor c = db.rawQuery(sql,null);

            int count= c.getCount(); // rows number

            c.moveToFirst();

            MyPlayers player = new MyPlayers();

            if(count > 0)
            {
                player.setId(c.getInt(0));
                player.setName(c.getString(1));
                player.setScore(c.getInt(2));


                return player;
            }
            else
                return null;
        }
        //----------------------------------------
        public List<MyPlayers> GetAllPlayers()
        {
            List l= new ArrayList();


            // get all data from datatable

            Cursor my_cursor=db.query(DataBaseHelper.BOARD, ContactTable_Fields, null, null, null, null, null);

            my_cursor.moveToFirst();

            while(!my_cursor.isAfterLast())
            {
                MyPlayers player = new MyPlayers();

                player.setId(my_cursor.getInt(0));
                player.setName(my_cursor.getString(1));
                player.setScore(my_cursor.getInt(2));

                l.add(player);

                my_cursor.moveToNext();
            }

            my_cursor.close();

            return l;
        }
        //-----------------------------------------------
        public void Add_New_Player(MyPlayers p)
        {
            ContentValues myValues= new ContentValues();

            myValues.put(dbhelper.PLAYER_NAME,p.getName());
            myValues.put(dbhelper.PLAYER_SCORE, p.getScore());



            db.insert(dbhelper.BOARD, null, myValues);

        }
        //---------------------------------------------------------
        public void EditPlayer(MyPlayers p)
        {
            String sql="Update " + this.dbhelper.BOARD + " set " + dbhelper.PLAYER_NAME + "='" +

                    p.getName() + "'," + dbhelper.PLAYER_SCORE + "='" +

                    dbhelper.PLAYER_ID + "=" + p.getId();

            db.execSQL(sql);
        }
        public void DeletePlayer(int id)
        {
            String sql="Delete from " + this.dbhelper.BOARD + " Where " +

                    dbhelper.PLAYER_ID + "=" + id;

            db.execSQL(sql);
        }

       public void DeleteTable()
        {
            db.execSQL("DROP TABLE IF EXISTS " + dbhelper.BOARD);

        }
    }


