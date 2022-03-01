package com.giragwe.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {
    static String DB_NAME="SpeedQuiz.dp";
    static int DB_VERSION=1;

    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context,DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"Le Java est une language de programmation\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Le Javascript est un cousin du Java\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Le CSS est un language de programmation\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Le HTML est un langage de balisage\", 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
