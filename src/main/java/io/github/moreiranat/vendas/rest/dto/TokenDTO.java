package io.github.moreiranat.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO { //representa o retorno para o usuario

    private String login;
    private String token;
}
