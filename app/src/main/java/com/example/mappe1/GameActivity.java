package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity{

    String answer = "";
    int currentIndex = 0;
    ArrayList<Sporsmaal> sporsmaals = new ArrayList<Sporsmaal>();

    Sporsmaal spm1 = new Sporsmaal("1+1","2", false);
    Sporsmaal spm2 = new Sporsmaal("4+5","9", false);
    Sporsmaal spm3 = new Sporsmaal("7+8","15", false);
    Sporsmaal spm4 = new Sporsmaal("8+3","11", false);
    Sporsmaal spm5 = new Sporsmaal("9+9","18", false);
    Sporsmaal spm6 = new Sporsmaal("123+123","246", false);
    Sporsmaal spm7 = new Sporsmaal("321+321","642", false);
    Sporsmaal spm8 = new Sporsmaal("111+111","222", false);
    Sporsmaal spm9 = new Sporsmaal("101-100","1", false);
    Sporsmaal spm10 = new Sporsmaal("54-5","49", false);
    Sporsmaal spm11 = new Sporsmaal("12x12","144", false);
    Sporsmaal spm12 = new Sporsmaal("12/6","2", false);
    Sporsmaal spm13 = new Sporsmaal("87+3","90", false);
    Sporsmaal spm14 = new Sporsmaal("34-20","14", false);
    Sporsmaal spm15 = new Sporsmaal("22+22","44", false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getData();
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

        sporsmaals.add(spm1);
        sporsmaals.add(spm2);
        sporsmaals.add(spm3);
        sporsmaals.add(spm4);
        sporsmaals.add(spm5);
        sporsmaals.add(spm6);
        sporsmaals.add(spm7);
        sporsmaals.add(spm8);
        sporsmaals.add(spm9);
        sporsmaals.add(spm10);
        sporsmaals.add(spm11);
        sporsmaals.add(spm12);
        sporsmaals.add(spm13);
        sporsmaals.add(spm14);
        sporsmaals.add(spm15);

        TextView spm = (TextView) findViewById(R.id.spmView);
        spm.setText(sporsmaals.get(currentIndex).sporsmaal);
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
                spm.setText(sporsmaals.get(currentIndex+1).sporsmaal);
                currentIndex++;
                sjekkSvar(currentIndex, ((String) answers.getText()));
                System.out.println((String) answers.getText());
                System.out.println(sporsmaals.get(currentIndex).svartRiktig);
                break;
            case R.id.button_forrige:
                try {
                    spm.setText(sporsmaals.get(currentIndex - 1).sporsmaal);
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

    public void sjekkSvar(int curIndex, String svar){
        String riktigSvar = sporsmaals.get(curIndex).riktigSvar;
        if (svar.equals(riktigSvar)){
            sporsmaals.get(curIndex).setSvartRiktig(true);
        }
    }
}
