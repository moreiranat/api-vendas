package io.github.moreiranat.vendas.rest.controller;

import io.github.moreiranat.vendas.domain.entity.Usuario;
import io.github.moreiranat.vendas.exception.SenhaInvalidaException;
import io.github.moreiranat.vendas.rest.dto.CredenciaisDTO;
import io.github.moreiranat.vendas.rest.dto.TokenDTO;
import io.github.moreiranat.vendas.security.jwt.JwtService;
import io.github.moreiranat.vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor //gera um construtor utilizando o argumento final
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada); //quando for para o Usuario service, ele já vai com a senha criptografada
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth") //Post é mais seguro para enviar informacoes, nao usar Get
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) { //metodo que vai fazer a autenticacao
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
