package com.lamego.sistemabancario.domain.entities;

import com.lamego.sistemabancario.R;

public class ContaEspecial extends ContaBancaria{

    /**
     *@author: André O. Lamego
     */

    private float limite;

    public ContaEspecial(String cliente, int num_conta, float saldo, float limite) {
        super(cliente, num_conta, saldo);
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if(getSaldo() - valor < 0){
            float valorRestante = valor - getSaldo();
            if(valorRestante > limite)
                return false;
        }

        setSaldo(getSaldo() - valor);
        return true;
    }

    public float getLimite() {
        return limite;
    }
    public void setLimite(float limite) {
        this.limite = limite;
    }
}
