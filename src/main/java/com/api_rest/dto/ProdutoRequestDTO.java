package com.api_rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class ProdutoRequestDTO {
    @NotBlank(message = "Não tem nada aqui")
    @NotNull(message = "O campo nome não pode ser null.")
    private String nome;

    @NotNull(message = "O campo descrição está null.")
    @Size(min = 20, message = "A descrição deve conter no mínimo 20 caracteres")
    private String descricao;

    @NotNull(message = "O campo preco não pode ser null")
    @DecimalMin(value = "0.01", message = "O campo preço deve ser maior que 0")
    private Double preco;

    @NotNull(message = "O campo estoque está null.")
    @Min(value = 1, message = "O campo quantidadeEstoque deve ser maior que 0")
    @Column(name = "quantidadeestoque")
    private Integer quantidadeEstoque;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}


