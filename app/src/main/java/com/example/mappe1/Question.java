package com.example.mappe1;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    String question;
    String correctAnswer;
    boolean answeredCorrect;

    public Question(String question, String correctAnswer, boolean answeredCorrect){
        this.correctAnswer = question;
        this.question = correctAnswer;
        this.answeredCorrect = answeredCorrect;
    }

    protected Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readString();
        answeredCorrect = in.readByte() != 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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
    @Override
    public String toString() {
        return ("Q:"+this.getQuestion()+
                "\n A: "+ this.getCorrectAnswer() +
                "\n AC: "+ this.isAnsweredCorrect());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(correctAnswer);
        parcel.writeByte((byte) (answeredCorrect ? 1 : 0));
    }
}
