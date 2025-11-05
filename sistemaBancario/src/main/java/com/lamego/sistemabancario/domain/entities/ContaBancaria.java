package com.lamego.sistemabancario.domain.entities;

import com.lamego.sistemabancario.R;

public abstract class ContaBancaria {

    /**
     *@author: André O. Lamego
     */

    private String cliente;
    private int num_conta;
    private float saldo;

    public ContaBancaria() {
        super();
    }

    public ContaBancaria(String cliente, int num_conta, float saldo) {
        this.cliente = cliente;
        this.num_conta = num_conta;
        this.saldo = saldo;
    }

    public boolean sacar(float valor) {
        if(saldo - valor < 0)
            return false;

        saldo -= valor;
        return true;
    }

    public void depositar(float valor) {
        saldo += valor;
    }

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }
    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
