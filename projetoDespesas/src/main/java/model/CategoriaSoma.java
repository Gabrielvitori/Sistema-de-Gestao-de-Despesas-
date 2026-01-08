package model;

public class CategoriaSoma {

    private String nomeCategoria;
    private Double totalGasto;

    public CategoriaSoma(String nomeCategoria, Double totalGasto) {
        this.nomeCategoria = nomeCategoria;
        this.totalGasto = totalGasto;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    @Override
    public String toString() {
        return "Categoria: " + nomeCategoria + " | Total: " + totalGasto;
    }
}