package com.giragwe.speedquiz.Models;

public class Question {
    private String question;
    private Boolean reponse;

    public Question(String question, Boolean reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public Boolean getReponse() {
        return reponse;
    }
}
