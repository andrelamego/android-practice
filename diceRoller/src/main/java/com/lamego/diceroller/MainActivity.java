package com.lamego.diceroller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private Spinner spDiceType;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbOne = findViewById(R.id.rbOne);
        rbOne.setChecked(true);
        rbTwo = findViewById(R.id.rbTwo);
        rbThree = findViewById(R.id.rbThree);
        spDiceType = findViewById(R.id.spDiceType);
        tvResult = findViewById(R.id.tvResult);

        preencheSpinner();

        Button btnRoll = findViewById(R.id.btnRoll);
        btnRoll.setOnClickListener(e -> roll());
    }

    private void roll() {
        StringBuffer result = new StringBuffer();
        String diceType = (String) spDiceType.getSelectedItem();
        int diceFaces = 0;
        int[] dices = new int[0];

        if(rbOne.isChecked()) {
            dices = new int[1];
        }
        else if(rbTwo.isChecked()) {
            dices = new int[2];
        }
        else if(rbThree.isChecked()) {
            dices = new int[3];
        }

        switch (diceType){
            case "D4":
                diceFaces = 4;
                break;
            case "D6":
                diceFaces = 6;
                break;
            case "D8":
                diceFaces = 8;
                break;
            case "D10":
                diceFaces = 10;
                break;
            case "D12":
                diceFaces = 12;
                break;
            case "D20":
                diceFaces = 20;
                break;
            case "D100":
                diceFaces = 100;
                break;
        }

        for (int i = 0; i < dices.length; i++) {
            dices[i] = (int) ((Math.random() * diceFaces)) + 1;
            result.append(dices[i]);
            if(i != dices.length - 1)
                result.append(", ");
        }

        tvResult.setText(result);
    }

    private void preencheSpinner() {
        List<String> lista = new ArrayList<>();
        lista = List.of("D4", "D6", "D8", "D10", "D12", "D20", "D100");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDiceType.setAdapter(adapter);
    }
}