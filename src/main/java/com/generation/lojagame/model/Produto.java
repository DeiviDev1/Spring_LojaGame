package com.generation.lojagame.model;




import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o atributo  nome é obrigatórioe e nao poder ser vazio!")
    @Size(min = 3, max = 50, message =  "O atributo nome deve conter no mínimo 3 e no máximo 50 caracteres")
    private String nome;


    @NotNull(message = "o atributo descrição pode ser vazio!")
    @Size(min = 5, max = 100, message =  "O atributo descrição deve conter no mínimo 5 e no máximo 100 caracteres")
    private String descricao;

    @NotNull( message = " Atributo preço é obrigatorio")
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
