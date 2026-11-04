package org.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {
    private SimpleStringProperty nome;
    private SimpleDoubleProperty preco;

    public Produto(String nome, double preco) {
        this.nome = new SimpleStringProperty(nome);
        this.preco = new SimpleDoubleProperty(preco);

    }

    public double getPreco() {
        return this.preco.get();
    }
    public String getNome(){
        return this.nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }
}
