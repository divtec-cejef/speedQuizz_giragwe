package com.giragwe.speedquiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Gestion de la base de donnée
 */
public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {
    static String DB_NAME="SpeedQuiz.dp";
    static int DB_VERSION=1;

    //Constructeur
    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context,DB_NAME, null,DB_VERSION);
    }

    /**
     * Création de la base de donnée
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);

        //insertions de valeurs dans la base de donnée
        db.execSQL("INSERT INTO quiz VALUES(1,\"Prêt ?\", 3)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Le Java est une language de programmation\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Le Javascript est un cousin du Java\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Le CSS est un language de programmation\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"Le HTML est un langage de balisage\", 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
