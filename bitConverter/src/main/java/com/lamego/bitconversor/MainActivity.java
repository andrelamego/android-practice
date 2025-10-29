package com.lamego.bitconversor;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etBits;
    private Spinner spTypes;
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

        etBits = findViewById(R.id.etBits);
        spTypes = findViewById(R.id.spTypes);
        tvResult = findViewById(R.id.tvResult);

        preencheSpinner();

        Button btnConvert = findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(e -> convert());
    }

    private void convert() {
        String type = (String) spTypes.getSelectedItem();
        long bytes = Long.parseLong(etBits.getText().toString());
        double result = 0;

        switch (type) {
            case "Bytes":
                result = bytes / 8;
                tvResult.setText(String.format("%s Bytes", result));
                break;
            case "KB":
                result = (bytes / (8 * Math.pow(10, 3)));
                tvResult.setText(String.format("%s KB", result));
                break;
            case "MB":
                result = (bytes / (8 * Math.pow(10, 6)));
                tvResult.setText(String.format("%s MB", result));
                break;
            case "GB":
                result = (bytes / (8 * Math.pow(10, 9)));
                tvResult.setText(String.format("%s GB", result));
                break;
            case "TB":
                result = (bytes / (8 * Math.pow(10, 12)));
                tvResult.setText(String.format("%s TB", result));
                break;
        }
    }

    private void preencheSpinner() {
        List<String> lista = List.of("Bytes", "KB", "MB", "GB", "TB");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypes.setAdapter(adapter);
    }
}