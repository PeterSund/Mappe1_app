package com.example.mappe1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity{

    private String answer = "";
    private int currentIndex = 0;

    //ARRAYS SKAL LIGGER I XML I FØLGE OPPGAVETEKST. Create from resources kan brukes tror jeg. createFromResource(), se prefAct linje 44
    private ArrayList<Question> gameQuestions = new ArrayList<Question>();
    private ArrayList<Question> allQuestions = new ArrayList<>();

    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getData();


        //Setter antall spørsmål i spillet fra verdi i SharedPref
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        int gameQuestionsArrayLength = preferences.getInt("questionArray_length", 15);

        //Sett språk fra SharedPreferences
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        SetLocaleLanguage setLocaleLanguage = new SetLocaleLanguage();
        setLocaleLanguage.setLanguage(preferences, res);

        setContentView(R.layout.game_activity);




        Button btn1 = (Button) findViewById(R.id.button_1);
        Button btn2 = (Button) findViewById(R.id.button_2);
        Button btn3 = (Button) findViewById(R.id.button_3);
        Button btn4 = (Button) findViewById(R.id.button_4);
        Button btn5 = (Button) findViewById(R.id.button_5);
        Button btn6 = (Button) findViewById(R.id.button_6);
        Button btn7 = (Button) findViewById(R.id.button_7);
        Button btn8 = (Button) findViewById(R.id.button_8);
        Button btn9 = (Button) findViewById(R.id.button_9);
        Button btn0 = (Button) findViewById(R.id.button_0);
        Button btnNeste = (Button) findViewById(R.id.button_neste);
        Button btnForrige = (Button) findViewById(R.id.button_forrige);

        btn1.setOnClickListener(this::onClick);
        btn2.setOnClickListener(this::onClick);
        btn3.setOnClickListener(this::onClick);
        btn4.setOnClickListener(this::onClick);
        btn5.setOnClickListener(this::onClick);
        btn6.setOnClickListener(this::onClick);
        btn7.setOnClickListener(this::onClick);
        btn8.setOnClickListener(this::onClick);
        btn9.setOnClickListener(this::onClick);
        btn0.setOnClickListener(this::onClick);
        btnNeste.setOnClickListener(this::onClick);
        btnForrige.setOnClickListener(this::onClick);

        Question spm1 = new Question("1+1","2", false);
        Question spm2 = new Question("4+5","9", false);
        Question spm3 = new Question("7+8","15", false);
        Question spm4 = new Question("8+3","11", false);
        Question spm5 = new Question("9+9","18", false);
        Question spm6 = new Question("123+123","246", false);
        Question spm7 = new Question("321+321","642", false);
        Question spm8 = new Question("111+111","222", false);
        Question spm9 = new Question("101-100","1", false);
        Question spm10 = new Question("54-5","49", false);
        Question spm11 = new Question("12x12","144", false);
        Question spm12 = new Question("12/6","2", false);
        Question spm13 = new Question("87+3","90", false);
        Question spm14 = new Question("34-20","14", false);
        Question spm15 = new Question("22+22","44", false);

        allQuestions.add(spm1);
        allQuestions.add(spm2);
        allQuestions.add(spm3);
        allQuestions.add(spm4);
        allQuestions.add(spm5);
        allQuestions.add(spm6);
        allQuestions.add(spm7);
        allQuestions.add(spm8);
        allQuestions.add(spm9);
        allQuestions.add(spm10);
        allQuestions.add(spm11);
        allQuestions.add(spm12);
        allQuestions.add(spm13);
        allQuestions.add(spm14);
        allQuestions.add(spm15);

        alterQuetionsList(gameQuestionsArrayLength);

        TextView spm = (TextView) findViewById(R.id.spmView);
        spm.setText(gameQuestions.get(currentIndex).question);

    }

    public void onClick(View view) {
        TextView answers = (TextView) findViewById(R.id.svar);
        TextView spm = (TextView) findViewById(R.id.spmView);
        switch (view.getId()){
            case R.id.button_0:
                answer += "0";
                answers.setText(answer);
                break;
            case R.id.button_1:
                answer += "1";
                answers.setText(answer);
                break;
            case R.id.button_2:
                answer += "2";
                answers.setText(answer);
                break;
            case R.id.button_3:
                answer += "3";
                answers.setText(answer);
                break;
            case R.id.button_4:
                answer += "4";
                answers.setText(answer);
                break;
            case R.id.button_5:
                answer += "5";
                answers.setText(answer);
                break;
            case R.id.button_6:
                answer += "6";
                answers.setText(answer);
                break;
            case R.id.button_7:
                answer += "7";
                answers.setText(answer);
                break;
            case R.id.button_8:
                answer += "8";
                answers.setText(answer);
                break;
            case R.id.button_9:
                answer += "9";
                answers.setText(answer);
                break;
            case R.id.button_neste:
                answer = "";
                answers.setText(answer);
                spm.setText(gameQuestions.get(currentIndex+1).question);
                currentIndex++;
                checkAnswer(currentIndex, ((String) answers.getText()));
                System.out.println((String) answers.getText());
                System.out.println(gameQuestions.get(currentIndex).answeredCorrect);
                break;
            case R.id.button_forrige:
                try {
                    spm.setText(gameQuestions.get(currentIndex - 1).question);
                    currentIndex--;
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;
            default:
                break;
        }
    }

    public void checkAnswer(int curIndex, String answer){
        String correctAnswer = gameQuestions.get(curIndex).correctAnswer;
        if (answer.equals(correctAnswer)){
            gameQuestions.get(curIndex).setAnsweredCorrect(true);
        }
    }

    public void alterQuetionsList(int gameQuestionsArrayLength) {
        Random r = new Random();
        for(int i=0; i < gameQuestionsArrayLength; i++) {
            Integer qnr = r.nextInt(15 - 1) + 1;
            gameQuestions.add(allQuestions.get(qnr));
        }
    }
}
