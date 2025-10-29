package com.lamego.autonomiacalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    /*
     *  @author: André O. Lamego
     */

    private EditText etConsumo;
    private EditText etCombustivel;
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

        etConsumo = findViewById(R.id.etConsumo);
        etCombustivel = findViewById(R.id.etCombustivel);
        tvResult = findViewById(R.id.tvResult);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(e -> {
            Float consumo = Float.parseFloat(etConsumo.getText().toString());
            Float combustivel = Float.parseFloat(etCombustivel.getText().toString());

            Float autonomia = (consumo * combustivel) * 1000;

            String result = autonomia + "m";
            tvResult.setText(result);

        });
    }
}