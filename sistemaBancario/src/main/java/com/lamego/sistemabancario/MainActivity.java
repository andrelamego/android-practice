package com.lamego.sistemabancario;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lamego.sistemabancario.domain.entities.ContaBancaria;
import com.lamego.sistemabancario.domain.entities.ContaEspecial;
import com.lamego.sistemabancario.domain.entities.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    /**
     *@author: André O. Lamego
     */

    private EditText etNome;
    private EditText etCodigo;
    private EditText etSaldo;
    private EditText etAtributo;
    private EditText etValor;
    private EditText etTaxaRendimento;

    private RadioButton rbPoupanca;
    private RadioButton rbEspecial;

    private Button btnCriarConta;
    private Button btnSaque;
    private Button btnDeposito;
    private Button btnDadosConta;
    private Button btnNovoSaldo;

    private TextView tvResults;

    private ContaBancaria conta;


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

        initializeViews();
        rbEspecial.setChecked(true);
        rbEspecial.setOnClickListener(e -> {
            etAtributo.setHint(R.string.limit);
            etAtributo.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            etAtributo.setText(null);
            etAtributo.requestFocus();
        });

        rbPoupanca.setOnClickListener(e -> {
            etAtributo.setHint(R.string.yield_day);
            etAtributo.setInputType(InputType.TYPE_CLASS_NUMBER);
            etAtributo.setText(null);
            etAtributo.requestFocus();
        });

        btnCriarConta.setOnClickListener(e -> criarConta());
        btnSaque.setOnClickListener(e -> realizarSaque());
        btnDeposito.setOnClickListener(e -> realizarDeposito());
        btnDadosConta.setOnClickListener(e -> mostrarDadosConta());
        btnNovoSaldo.setOnClickListener(e -> calcularNovoSaldo());
    }

    private void calcularNovoSaldo() {
        if(conta == null) {
            Toast.makeText(this, R.string.null_account_error, Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(conta instanceof ContaPoupanca)){
            Toast.makeText(this, R.string.wrong_account_type, Toast.LENGTH_SHORT).show();
            return;
        }

        String taxaRendimentoString = etTaxaRendimento.getText().toString();
        if(taxaRendimentoString.isEmpty()){
            Toast.makeText(this, R.string.null_yield_error, Toast.LENGTH_SHORT).show();
            return;
        }

        float taxaRendimento = Float.parseFloat(taxaRendimentoString);
        ((ContaPoupanca) conta).calcularNovoSaldo(taxaRendimento);
        String msg = getString(R.string.new_balance_update, conta.getSaldo());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        tvResults.setText("R$ " + conta.getSaldo());
    }

    private void mostrarDadosConta() {
        if(conta == null) {
            Toast.makeText(this, R.string.null_account_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String cliente = conta.getCliente();
        int numConta = conta.getNum_conta();
        float saldo = conta.getSaldo();

        String dados = getString(R.string.show_account_data, cliente, numConta, saldo);
        Toast.makeText(this, dados, Toast.LENGTH_LONG).show();
    }

    private void realizarDeposito() {
        if(conta == null) {
            Toast.makeText(this, R.string.null_account_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String valorString = etValor.getText().toString();
        if(valorString.isEmpty()){
            Toast.makeText(this, R.string.null_value_error, Toast.LENGTH_SHORT).show();
            return;
        }

        float valor = Float.parseFloat(valorString);
        conta.depositar(valor);
        Toast.makeText(this, R.string.withdraw_success, Toast.LENGTH_SHORT).show();

        tvResults.setText("R$ " + conta.getSaldo());
    }

    private void realizarSaque() {
        if(conta == null) {
            Toast.makeText(this, R.string.null_account_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String valorString = etValor.getText().toString();
        if(valorString.isEmpty()){
            Toast.makeText(this, R.string.null_value_error, Toast.LENGTH_SHORT).show();
            return;
        }

        float valor = Float.parseFloat(valorString);
        boolean success = conta.sacar(valor);

        if(success)
            Toast.makeText(this, R.string.withdraw_success, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.insufficient_balance, Toast.LENGTH_SHORT).show();

        tvResults.setText("R$ " + conta.getSaldo());
    }

    private void criarConta() {
        String nome = etNome.getText().toString();
        String codigoString = etCodigo.getText().toString();
        String saldoString = etSaldo.getText().toString();
        String atributoString = etAtributo.getText().toString();

        if(nome.isEmpty() || codigoString.isEmpty() || saldoString.isEmpty() || atributoString.isEmpty()){
            Toast.makeText(this, R.string.register_error, Toast.LENGTH_SHORT).show();
            return;
        }

        int codigo = Integer.parseInt(codigoString);
        float saldo = Float.parseFloat(saldoString);

        if(rbEspecial.isChecked()) {
            float limite = Float.parseFloat(atributoString);
            conta = new ContaEspecial(nome, codigo, saldo, limite);
        }
        else if (rbPoupanca.isChecked()) {
            int dia_rendimento = Integer.parseInt(atributoString);
            conta = new ContaPoupanca(nome, codigo, saldo, dia_rendimento);
        }

        etNome.setText(null);
        etCodigo.setText(null);
        etSaldo.setText(null);
        etAtributo.setText(null);
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
    }

    private void initializeViews() {
        etNome = findViewById(R.id.etNome);
        etCodigo = findViewById(R.id.etCodigo);
        etSaldo = findViewById(R.id.etSaldo);
        etAtributo = findViewById(R.id.etAtributoAdicional);
        etValor = findViewById(R.id.etValor);
        etTaxaRendimento = findViewById(R.id.etTaxaRendimento);

        rbEspecial = findViewById(R.id.rbEspecial);
        rbPoupanca = findViewById(R.id.rbPoupanca);

        btnCriarConta = findViewById(R.id.btnCriarConta);
        btnSaque = findViewById(R.id.btnSaque);
        btnDeposito = findViewById(R.id.btnDeposito);
        btnDadosConta = findViewById(R.id.btnDadosConta);
        btnNovoSaldo = findViewById(R.id.btnNovoSaldo);

        tvResults = findViewById(R.id.tvResults);
    }
}