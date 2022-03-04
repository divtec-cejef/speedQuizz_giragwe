package com.giragwe.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.giragwe.speedquiz.Models.Question;
import com.giragwe.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    ArrayList<Question> questionsList;

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

    /**
     * Renvoie une question au hasard dans la liste
     * @return une question au hasard
     */
    public Question getRandomQuestion() {
        int index = getRandomIndex(questionsList);
        Question question = questionsList.get(index);

        //Supprime la question de la liste afin que la question ne soit pas posée 2 fois
        questionsList.remove(index);

        return question;
    }

    /**
     * Renvoie la première question de la liste
     * @return la première question
     */
    public Question getFirstQuestion() {
        Question question = questionsList.get(0);

        //Supprime la question de la liste afin que la question ne soit pas posée 2 fois
        questionsList.remove(0);
        return  question;
    }

    /**
     * Donne un index d'un élément de la liste au hasard
     * @param questionList liste de question
     * @return index au hasard d'un élément de la liste
     */
    private int getRandomIndex(ArrayList<Question> questionList) {
        Random random = new Random();
        return random.nextInt(questionList.size());
    }


}
