package com.example.mappe1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompatActivity{

    private String answer = "";
    private int currentIndex = 0;

    //ARRAYS SKAL LIGGER I XML I FØLGE OPPGAVETEKST. Create from resources kan brukes tror jeg. createFromResource(), se prefAct linje 44
    private ArrayList<Question> gameQuestions = new ArrayList<>();
    private ArrayList<Question> allQuestions = new ArrayList<>();

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferences_editor;



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

        fetchQuestionsFromXML(allQuestions);

        alterQuestionsList(gameQuestionsArrayLength);

        TextView spm = (TextView) findViewById(R.id.spmView);
        spm.setText(gameQuestions.get(currentIndex).question);

    }

    public void onClick(View view) {
        TextView answers = (TextView) findViewById(R.id.answerView);
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
                if (currentIndex == gameQuestions.size()-1){
                    preferences_editor = getSharedPreferences("Pref", MODE_PRIVATE).edit();
                    Set<String> scores = preferences.getStringSet("scores", null );
                    if (scores == null) {
                        Set<String> scores1 = new HashSet<>();
                        String scoreForSaving = "";
                        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                        scoreForSaving += "Game " + timeStamp;
                        String scoreCount = String.valueOf(correctAnswersCount());
                        String maxScoreCount = String.valueOf(gameQuestions.size());
                        scoreForSaving += "@" + scoreCount + "/" + maxScoreCount;
                        scores1.add(scoreForSaving);
                        preferences_editor.putStringSet("scores", scores1);
                        preferences_editor.apply();
                    } else {
                        String scoreForSaving = "";
                        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                        scoreForSaving += "Game " + timeStamp;
                        String scoreCount = String.valueOf(correctAnswersCount());
                        String maxScoreCount = String.valueOf(gameQuestions.size());
                        scoreForSaving += "@" + scoreCount + "/" + maxScoreCount;
                        scores.add(scoreForSaving);
                        preferences_editor.putStringSet("scores", scores);
                        preferences_editor.apply();
                    }

                    //LAGRE SCORE med DATO og Tidspunkt (feks. 21.02.1998 13:30)

                    confirmEndGameDialog();
                }
                else {
                    String a = answers.getText().toString();
                    System.out.println(a);
                    System.out.println(gameQuestions.get(currentIndex).answeredCorrect);
                    checkAnswer(currentIndex, a);
                    System.out.println(gameQuestions.get(currentIndex).answeredCorrect);
                    answer = "";
                    answers.setText(answer);
                    spm.setText(gameQuestions.get(currentIndex + 1).question);
                    currentIndex++;
                }
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
        System.out.println("A:" + correctAnswer);
        System.out.println("Test:" + answer);
        gameQuestions.get(curIndex).setAnsweredCorrect(answer.equals(correctAnswer));
    }

    public void alterQuestionsList(int gameQuestionsArrayLength) {
        Random r = new Random();
        for(int i=0; i < gameQuestionsArrayLength; i++) {
            int qnr = r.nextInt(15 - 1) + 1;
            gameQuestions.add(allQuestions.get(qnr));

            //FJERNE BRUKTE SPØRSMÅL!!!


        }
    }

    public void fetchQuestionsFromXML(ArrayList<Question> allQuestions) {
        List<String> qs = Arrays.asList(getResources().getStringArray(R.array.questions));
        for (int i = 0; i < qs.size(); i++){
            String [] a = qs.get(i).split(",");
            Question b = new Question(a[1], a[0], false);
            allQuestions.add(b);
        }
        for (int i = 0; i < allQuestions.size(); i++){
            System.out.println(allQuestions.get(i).toString());
        }
    }

    private void confirmEndGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogtekst_score + " " + correctAnswersCount() +"/"+ gameQuestions.size());
        builder.setPositiveButton("Prøv på nytt!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                recreate();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Gå til meny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //RERDIRECT TIL MENY
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int correctAnswersCount(){
        int c = 0;
        for (int i = 0; i < gameQuestions.size(); i++) {
            if(gameQuestions.get(i).answeredCorrect){
                c++;
            }
        }
        return c;
    }
}
