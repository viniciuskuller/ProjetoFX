package org.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Produto {
    private SimpleStringProperty nome;
    private SimpleDoubleProperty precoAtacado;
    private SimpleDoubleProperty precoVenda;
    private SimpleIntegerProperty quantidade;
    private SimpleObjectProperty<LocalDate> diaLote;
    private SimpleObjectProperty<LocalDate> diaValidade;

    public Produto(String nome, double precoAtacado, double precoVenda, int quantidade, LocalDate diaLote, LocalDate diaValidade) {
        this.nome = new SimpleStringProperty(nome);
        this.precoAtacado = new SimpleDoubleProperty(precoAtacado);
        this.precoVenda = new SimpleDoubleProperty(precoVenda);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.diaLote = new SimpleObjectProperty<>(diaLote);
        this.diaValidade = new SimpleObjectProperty<>(diaValidade);
    }


    public double getPrecoAtacado() {
        return this.precoAtacado.get();
    }
    public void setPrecoAtacado(double precoAtacado) {
        this.precoAtacado.set(precoAtacado);
    }

    public SimpleDoubleProperty precoAtacadoProperty() {
        return precoAtacado;
    }

    public String getNome(){
        return this.nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }


    public double getPrecoVenda () {
        return precoVenda.get();
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda.set(precoVenda);
    }

    public SimpleDoubleProperty precoVendaProperty() {
        return precoVenda;
    }

    public LocalDate getDiaValidade() {
        return diaValidade.get();
    }

    public void setDiaValidade(LocalDate diaValidade) {
        if(diaValidade.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data de validade já foi.");
        }
        else {
            this.diaValidade.set(diaValidade);
        }
    }

    public SimpleObjectProperty<LocalDate> diaValidadeProperty() {
        return diaValidade;
    }

    public void setDiaLote(LocalDate diaLote) {
        if(diaLote.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data de lote está no futuro. ")
        }
        else {
            this.diaLote.set(diaLote);
        }
    }

    public LocalDate getDiaLote() {
        return diaLote.get();
    }

    public SimpleObjectProperty<LocalDate> diaLoteProperty() {
        return diaLote;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade < 0){
            this.quantidade.set(0);
        }
        else {
            this.quantidade.set(quantidade);
        }
    }

    public int getQuantidade () {
        return quantidade.get();
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }
    public long getDiasRestantes(){
        LocalDate hoje = LocalDate.now();
        return ChronoUnit.DAYS.between(now, getDiaValidade());
    }

}