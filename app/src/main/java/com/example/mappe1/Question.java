package com.example.mappe1;

public class Question {
    String question;
    String correctAnswer;
    boolean answeredCorrect;

    public Question(String question, String correctAnswer, boolean answeredCorrect){
        this.correctAnswer = question;
        this.question = correctAnswer;
        this.answeredCorrect = answeredCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isAnsweredCorrect() {
        return answeredCorrect;
    }

    public void setAnsweredCorrect(boolean answeredCorrect) {
        this.answeredCorrect = answeredCorrect;
    }
}
