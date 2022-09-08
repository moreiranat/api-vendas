package io.github.moreiranat.vendas.service.impl;

import io.github.moreiranat.vendas.domain.entity.Usuario;
import io.github.moreiranat.vendas.domain.repository.UsuarioRepository;
import io.github.moreiranat.vendas.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository; //vai precisar carregar um usuario pelo username

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //verificar se a senha do usuario esta batendo com a senha que esta vindo do banco de dados
    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword()); //parametros: senha digitada (senha que vai ser comparada), senha codificada, criptografada, que vem do banco de dados

        if(senhasBatem) {
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        //usuario isAdmin? se ele for admin, vai retornar para ele um array de String contendo as 2 roles (ADMIN e USER),
        //caso contrario, vai retornar um array de String contendo só a role USER
        String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
