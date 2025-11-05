package com.lamego.sistemabancario.domain.entities;

public class ContaPoupanca extends ContaBancaria{

    /**
     *@author: André O. Lamego
     */

    private int dia_rendimento;

    public ContaPoupanca(String cliente, int num_conta, float saldo, int dia_rendimento) {
        super(cliente, num_conta, saldo);
        this.dia_rendimento = dia_rendimento;
    }

    public void calcularNovoSaldo(float taxa_rendimento) {
        float novoSaldo = getSaldo() * (1 + taxa_rendimento);
        setSaldo(novoSaldo);
    }

    public int getDia_rendimento() {
        return dia_rendimento;
    }
    public void setDia_rendimento(int dia_rendimento) {
        this.dia_rendimento = dia_rendimento;
    }
}
