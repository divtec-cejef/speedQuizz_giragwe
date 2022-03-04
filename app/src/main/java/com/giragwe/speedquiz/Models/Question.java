package com.giragwe.speedquiz.Models;

import android.database.Cursor;

/**
 * Classe représentant une Question
 */
public class Question {
    private String question;
    private int reponse;

    /**
     * Construit une question
     * @param cursor
     */
    public Question(Cursor cursor){
        this.question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        this.reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    /**
     * Retourne l'énoncé de la question
     * @return l'énoncé
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Retourne la réponse à la question
     * @return la question
     */
    public int getReponse() {
        return reponse;
    }
}
