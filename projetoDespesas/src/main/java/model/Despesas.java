package model;

import java.time.LocalDate;

public class Despesas {

    private Integer id;
    private String descricao;
    private LocalDate data;
    private Double valor;

    private Categoria categoria;

    public Despesas(){}

    public Despesas(Categoria categoria, LocalDate data, String descricao, Integer id, Double valor) {
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
        this.id = id;
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                ", categoria=" + (categoria != null ? categoria.getName() : "Nula") +
                '}';
    }
}
