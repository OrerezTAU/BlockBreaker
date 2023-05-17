package com.example.tehem.blockbreaker;

import java.io.Serializable;

/**
 * Created by tehem on 28/02/2016.
 */


    public class MyPlayers implements Serializable // without Serializable i cant send object
            // from this class throught putextra
    {
        private int id;

        private String name;

        private int score;



        public MyPlayers()
        {



        }
        //--------------------------------

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;


        }
    }


