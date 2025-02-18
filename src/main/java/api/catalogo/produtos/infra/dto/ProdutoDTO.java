package api.catalogo.produtos.infra.dto;

import java.time.LocalDateTime;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private String valor;
    private Double quantidadeEstoque;
    private Double quantidadeReservada;
    private LocalDateTime horaImportacao;

    public ProdutoDTO(Long id, String nome, String tipo, String descricao, String valor, Double quantidadeEstoque, Double quantidadeReservada, LocalDateTime horaImportacao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeReservada = quantidadeReservada;
        this.horaImportacao = horaImportacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getQuantidadeReservada() {
        return quantidadeReservada;
    }

    public void setQuantidadeReservada(Double quantidadeReservada) {
        this.quantidadeReservada = quantidadeReservada;
    }

    public LocalDateTime getHoraImportacao() {
        return horaImportacao;
    }

}
