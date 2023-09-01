package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listaTodos();

    Usuario cadastra(CadastroUsuarioRequest cadastroUsuarioRequest);

    Usuario buscarUsuarioPorIdentificador(String identificador);

}
