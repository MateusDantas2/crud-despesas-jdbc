package br.com.codandosimples.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Despesa {

    private Long id;
    private String descricao;
    private LocalDate data;
    private double valor;
    private Categoria categoria;

    public Despesa(Long id, String descricao, LocalDate data, double valor, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Despesa(String descricao, LocalDate data, double valor, Categoria categoria) {
        this(null, descricao, data, valor, categoria);
    }
}