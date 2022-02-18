package com.giragwe.speedquiz.Controllers;

import com.giragwe.speedquiz.Models.Question;

import java.util.ArrayList;

public class QuestionManager {
    public ArrayList<Question> createQuestion() {
        ArrayList<Question> questionsList = new ArrayList<>();

        questionsList.add(new Question("Le Java est une language de programmation", true));
        questionsList.add(new Question("Le Javascript est un cousin du Java", false));
        questionsList.add(new Question("Le CSS est un language de programmation", false));
        questionsList.add(new Question("Le HTML est un langage de balisage", true));

        return questionsList;
    }


}
