package com.giragwe.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.giragwe.speedquiz.Models.Question;
import com.giragwe.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    ArrayList<Question> questionsList = new ArrayList<>();

    public QuestionManager(Context context) {
        questionsList = initQuestionList(context);
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idQuiz",null);
        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();
        return listQuestion;
    }

    public Question getRandomQuestion() {
        int index = getRandomIndex(questionsList);
        Question question = questionsList.get(index);
        questionsList.remove(index);

        return question;
    }

    private int getRandomIndex(ArrayList<Question> questionList) {
        Random random = new Random();
        return random.nextInt(questionList.size());
    }


}
