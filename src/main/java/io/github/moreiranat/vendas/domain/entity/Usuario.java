package io.github.moreiranat.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    @Column
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Getter(onMethod = @__({@JsonIgnore})) //essa anotacao esconde a senha, nao mostra para o cliente
    @Setter(onMethod = @__({@JsonProperty})) //essa anotacao esconde a senha, nao mostra para o cliente. No caso, seta a senha, mas quando retorna, nao mostra
    private  String senha;
    @Column
    private boolean admin; //se a coluna admin for true, o usuario é administrador e ele vai ter a role ADMIN, caso contrário, ele só vai ter a role USER
}
