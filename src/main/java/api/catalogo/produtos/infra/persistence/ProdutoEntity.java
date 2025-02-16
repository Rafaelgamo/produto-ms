package api.catalogo.produtos.infra.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private String valor;
    private Double quantidadeEstoque;
    private Double quantidadeReservada;
    private LocalDateTime horaImportacao;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id, String nome, String valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;

    }

    public ProdutoEntity(String nome, String tipo, String descricao, String valor, Double quantidadeEstoque, Double quantidadeReservada, LocalDateTime horaImportacao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeReservada = quantidadeReservada;
        this.horaImportacao = horaImportacao;
    }

    public ProdutoEntity(Long id, String nome, String tipo, String descricao, String valor, Double quantidadeEstoque, Double quantidadeReservada, LocalDateTime horaImportacao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeReservada = quantidadeReservada;
        this.horaImportacao = horaImportacao;
    }

    public ProdutoEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Double getQuantidadeEstoque() {
        return this.quantidadeEstoque;
    }

    public LocalDateTime getHoraImportacao() {
        return horaImportacao;
    }

    public void setHoraImportacao(LocalDateTime horaImportacao) {
        this.horaImportacao = horaImportacao;
    }

    public Double getQuantidadeReservada() {
        return quantidadeReservada;
    }

    public void setQuantidadeReservada(Double quantidadeReservada) {
        this.quantidadeReservada = quantidadeReservada;
    }
}






