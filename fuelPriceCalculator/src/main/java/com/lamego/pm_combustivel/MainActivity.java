package com.lamego.pm_combustivel;

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
    * @author: André O. Lamego
    */

    private EditText etGas;
    private EditText etEthanol;
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

        etGas = findViewById(R.id.etGas);
        etEthanol = findViewById(R.id.etEthanol);
        tvResult = findViewById(R.id.tvResult);
        Button btnVerify = findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(e -> {
            float gasPrice = Float.parseFloat(etGas.getText().toString());
            float ethanolPrice = Float.parseFloat(etEthanol.getText().toString());

            if(ethanolPrice <= (gasPrice * 0.7)) {
                tvResult.setText(R.string.resultEthanol);
            }
            else {
                tvResult.setText(R.string.resultGas);
            }
        });
    }
}