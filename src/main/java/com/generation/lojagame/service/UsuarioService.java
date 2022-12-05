package com.generation.lojagame.service;


import com.generation.lojagame.model.Usuario;
import com.generation.lojagame.model.UsuarioLogin;
import com.generation.lojagame.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {


    @Autowired
    public UsuarioRepository usuarioRepository;

    /*cadastrar usuario*/
    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
        if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }
    /*atualizar usuario*/
    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
        if (usuario.isPresent()) {
            if (compararSenhas(usuarioLogin.get().getSenha(),usuario.get().getSenha())){
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));

                /*não fazer em produção*/
                usuarioLogin.get().setSenha(usuario.get().getSenha());
                return usuarioLogin;

            }
        }
        return Optional.empty();

    }

    private String gerarToken(String usuario, String senha) {
        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic "+ new String(tokenBase64);
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaBanco);
    }


    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }




}
