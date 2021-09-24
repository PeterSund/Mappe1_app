/*
Legge inn tysk i string XML - peter
TESTE - felles
RAPPORT - felles
KOMMENTERE SPAGHETTI - felles
*/

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompatActivity{

    private String answer = "";
    private int currentIndex = 0;
    private String countQuestion = "";

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
        //Default-verdien til spillengde er 5
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        int gameQuestionsArrayLength = preferences.getInt("questionArray_length", 5);

        //Sett språk fra SharedPreferences
        preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        Resources res = getResources();
        SetLocaleLanguage setLocaleLanguage = new SetLocaleLanguage();
        setLocaleLanguage.setLanguage(preferences, res);

        //Setter layout til game_activity.xml
        setContentView(R.layout.game_activity);

        //instansierer knapper
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
        Button btnForrige = (Button) findViewById(R.id.button_backspace);


        //instansierer onClick-funksjoner til knappene
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


        //Henter state hvis det finnes, hvis ikke så starter den et nytt spill
        if (savedInstanceState != null) {
            // restore value of members from saved state
            currentIndex = savedInstanceState.getInt("index");
            answer = savedInstanceState.getString("answer");
            gameQuestions = savedInstanceState.getParcelableArrayList("gameQuestions");
            countQuestion = savedInstanceState.getString("countQuestions");
            TextView answers = (TextView) findViewById(R.id.answerView);
            answers.setText(answer);
        }
        else{
            fetchQuestionsFromXML(allQuestions);

            alterQuestionsList(gameQuestionsArrayLength);

            countQuestion = (currentIndex+1) + "/" + (gameQuestions.size());
        }
        TextView spm = (TextView) findViewById(R.id.spmView);
        spm.setText(gameQuestions.get(currentIndex).question);

        TextView count = (TextView) findViewById(R.id.questionCount);
        count.setText(countQuestion);
    }

    //Lagrer state slik at dataene endres hvis feks orientation endres eller man går inn og ut av appen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("answer", answer);
        outState.putInt("index", currentIndex);
        outState.putParcelableArrayList("gameQuestions", gameQuestions);
        outState.putString("countQuestions", countQuestion);
    }

    //Metode som håndterer hva som skjer når man trykker på de forskjellige knappene i game_activity
    public void onClick(View view) {
        TextView answers = (TextView) findViewById(R.id.answerView);
        TextView spm = (TextView) findViewById(R.id.spmView);
        TextView count = (TextView) findViewById(R.id.questionCount);
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
                String a = answers.getText().toString();
                countQuestion = (currentIndex+2) + "/" + (gameQuestions.size());
                if (currentIndex == gameQuestions.size()-1) {
                    checkAnswer(currentIndex, a);
                    saveScoreToSharedPreferences();
                    confirmEndGameDialog();
                }
                else {
                    System.out.println(a);
                    System.out.println(gameQuestions.get(currentIndex).answeredCorrect);
                    checkAnswer(currentIndex, a);
                    System.out.println(gameQuestions.get(currentIndex).answeredCorrect);
                    answer = "";
                    answers.setText(answer);
                    spm.setText(gameQuestions.get(currentIndex + 1).question);
                    currentIndex++;
                    count.setText(countQuestion);
                }
                break;

            case R.id.button_backspace:
                try {
                    if (answers.getText() == null) {
                        break;
                    }
                    answer = backspace(answer);
                    answers.setText(answer);
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;

            case R.id.home:
                String string = getString(R.string.ja);
                String string2 = getString(R.string.nei);
                String string3 = getString(R.string.backpop);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.dialogtekst_score) + " " + correctAnswersCount() +"/"+ gameQuestions.size());
                builder.setPositiveButton(string, new DialogInterface.OnClickListener() {
                    //Metode som håndterer hva som skjer når man trykker på knappen i dialog-boksen
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton(string2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            default:
                break;
        }
    }

    //Metode som sjekker om svaret man har skrevet er korrekt og hvis det er det setter den
    //boolean i Question-objektet til å være true
    public void checkAnswer(int curIndex, String answer){
        String correctAnswer = gameQuestions.get(curIndex).correctAnswer;
        System.out.println("A:" + correctAnswer);
        System.out.println("Test:" + answer);
        gameQuestions.get(curIndex).setAnsweredCorrect(answer.equals(correctAnswer));
    }

    //Metode for å håndtere sletting av siste char i en string
    public String backspace(String text){
        StringBuilder ret = new StringBuilder();
        String[] q = text.split("");
        for(int i = 0; i<q.length-1; i++){
            ret.append(q[i]);
        }
        return ret.toString();
    }

    //Metode som håndterer hva som skjer hvis man trykker på back-knappen
    @Override
    public void onBackPressed() {
        int antallGjenvarendeSporsmal = gameQuestions.size() - currentIndex;
        String string = getString(R.string.ja);
        String string2 = getString(R.string.nei);
        String string3 = getString(R.string.backpop) +" "+ getString(R.string.duHar) +" "+ antallGjenvarendeSporsmal +" "+ getString(R.string.spmIgjen);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(string3);
        builder.setPositiveButton(string, new DialogInterface.OnClickListener() {
            //Metode som håndterer hva som skjer når man trykker på "JA" knappen i dialog-boksen
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton(string2, new DialogInterface.OnClickListener() {
            //Metode som håndterer hva som skjer når man trykker på "NEI" knappen i dialog-boksen
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Metode for å håndtere spørsmålene i spillet
    //Sørger for at ingen spørsmål kommer flere ganger og at de kommer i en tilfeldig rekkefølge
    //allQuestions inneholder alle spørsmål og gameQuestions er arrayet som blir brukt i spillet
    public void alterQuestionsList(int gameQuestionsArrayLength) {
        Random r = new Random();
        int j = allQuestions.size();
        for(int i=0; i < gameQuestionsArrayLength; i++) {
            int qnr = r.nextInt(j);
            j--;
            gameQuestions.add(allQuestions.get(qnr));
            allQuestions.remove(qnr);
        }
    }

    //Metode som henter spørsmål fra sporsmal.xml og legger alle inn i arrayet allQuestions
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

    //Metode som bygger og hånderer dialogboksen når man har svart på det siste spørsmålet i spillet
    private void confirmEndGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialogtekst_score) + " " + correctAnswersCount() +"/"+ gameQuestions.size());
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            //Metode som håndterer hva som skjer når man trykker på knappen i dialog-boksen
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    //Metode som lagrer scoren i SharedPreferances slik at scoren kan vises på statistikk siden
    //Henter ut String set for lagring av poeng, kjøres når spillet er ferdig4
    private void saveScoreToSharedPreferences() {
        preferences_editor = getSharedPreferences("Pref", MODE_PRIVATE).edit();
        Set<String> scores = preferences.getStringSet("scores", null );
        String localeLang = preferences.getString("localeLang", "en");

        //Bygger timestamp for lagring av spillstatistikk, lagrer med format for valgt språk. F. eks Søn for søndag hvis norsk er valgt
        String pattern = "EEEE dd. MMM";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale(localeLang));

        // Kjøres første gang man lagrer score da settet ikke finnes i sharedpreferences
        if (scores == null) {
            Set<String> scores1 = new HashSet<>();
            String scoreForSaving = "";
            String timeStamp = simpleDateFormat.format(new Date());
            scoreForSaving += timeStamp;
            String scoreCount = String.valueOf(correctAnswersCount());
            String maxScoreCount = String.valueOf(gameQuestions.size());
            scoreForSaving += "       " + scoreCount + "/" + maxScoreCount;
            scores1.add(scoreForSaving);
            preferences_editor.putStringSet("scores", scores1);
            preferences_editor.apply();
        } else {
            String scoreForSaving = "";
            String timeStamp = simpleDateFormat.format(new Date());
            scoreForSaving += timeStamp;
            String scoreCount = String.valueOf(correctAnswersCount());
            String maxScoreCount = String.valueOf(gameQuestions.size());
            scoreForSaving += "       " + scoreCount + "/" + maxScoreCount;
            scores.add(scoreForSaving);
            preferences_editor.putStringSet("scores", scores);
            preferences_editor.apply();
        }

        // Legger til poeng og maks poeng totalt
        int totalScore = preferences.getInt("totalScore", -1);
        int totalMaxScore = preferences.getInt("totalMaxScore", -1);

        //Kjøres første gang da feltene ikke ligger i sharedpref
        if (totalScore == -1 || totalMaxScore == -1) {
            int score = correctAnswersCount();
            preferences_editor.putInt("totalScore", score);
            preferences_editor.apply();

            int maxScore = gameQuestions.size();
            preferences_editor.putInt("totalMaxScore", maxScore);
            preferences_editor.apply();

        } else {
            totalScore = totalScore + correctAnswersCount();
            preferences_editor.putInt("totalScore", totalScore);
            preferences_editor.apply();

            totalMaxScore = totalMaxScore + gameQuestions.size();
            preferences_editor.putInt("totalMaxScore", totalMaxScore);
            preferences_editor.apply();
        }
    }

    //Metode som teller antall riktige svar og returnerer denne verdien
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
