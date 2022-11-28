package com.generation.lojagame.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank(message = "o atributo  produto é obrigatório!")
    @Size(min = 3, max = 100, message =  "O atributo produto deve conter no mínimo 03 e no máximo 100 caracteres")
    private String nome;


    @Size(min = 10, max = 100, message =  "O atributo produto deve conter no mínimo 10 e no máximo 100 caracteres")
    private String descricao;

    @NotNull( message = "o atributo preço não pode ser vazio")
    private Float preco;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
