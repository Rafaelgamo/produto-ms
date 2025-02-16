package api.catalogo.produtos.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Produto {

    private String nome;
    private String tipo;
    private String descricao;
    private String valor;
    private Double quantidadeEstoque;
    private Double quantidadeReservada;
    private LocalDateTime horaImportacao;


    public Produto(String nome, String tipo, String descricao, String valor, Double quantidadeEstoque, Double quantidadeReservada, LocalDateTime horaImportacao) {
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome Vazio");
        }
        if (tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo Vazio");
        }
        if (descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao Vazio");
        }
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("Valor Vazio");
        }
        if (quantidadeEstoque <= 0) {
            throw new IllegalArgumentException("Nescessario Quantidade ");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeReservada = quantidadeReservada;
        this.horaImportacao = horaImportacao;
    }



    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getValor() {return valor;}
    public void setValor(String valor) {this.valor = valor;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Double getQuantidadeEstoque() {return quantidadeEstoque;}
    public void setQuantidadeEstoque(Double quantidadeEstoque) {this.quantidadeEstoque = quantidadeEstoque;}

    public Double getQuantidadeReservada() {return quantidadeReservada;}
    public void setQuantidadeReservada(Double quantidadeReservada) {this.quantidadeReservada = quantidadeReservada;}

    public LocalDateTime getHoraImportacao() {return horaImportacao;}
    public void setHoraImportacao(LocalDateTime horaImportacao) {this.horaImportacao = horaImportacao;}


}
