package com.s340363_s344093.mappe1;

import android.os.Parcel;
import android.os.Parcelable;


//Klasse for objectet Question
//Klassen implementerer Parcelable for å kunne lagre gameQuestions<Question> arrayet når
//onPause() blir kjørt under gameActivity.
public class Question implements Parcelable {
    String question;
    String correctAnswer;
    boolean answeredCorrect;

    //konstruktør for Questions-klassen
    public Question(String question, String correctAnswer, boolean answeredCorrect){
        this.correctAnswer = question;
        this.question = correctAnswer;
        this.answeredCorrect = answeredCorrect;
    }

    //Metode som ble generert av Parcelable
    protected Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readString();
        answeredCorrect = in.readByte() != 0;
    }

    //Metode som ble generert av Parcelable
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

    //Gettere og settere for Questions-klassen
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

    //toString() som har blitt brukt for å teste innholdet av objektene i gameQuestions arrayet i gameActivity
    @Override
    public String toString() {
        return ("Q:"+this.getQuestion()+
                "\n A: "+ this.getCorrectAnswer() +
                "\n AC: "+ this.isAnsweredCorrect());
    }

    //Metode som ble generert av Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    //Metode som ble generert av Parcelable
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(correctAnswer);
        parcel.writeByte((byte) (answeredCorrect ? 1 : 0));
    }
}
