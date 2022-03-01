package com.giragwe.speedquiz.Models;

import android.database.Cursor;

public class Question {
    private String question;
    private int reponse;

    public Question(Cursor cursor){
        this.question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        this.reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getQuestion() {
        return question;
    }

    public int getReponse() {
        return reponse;
    }
}
