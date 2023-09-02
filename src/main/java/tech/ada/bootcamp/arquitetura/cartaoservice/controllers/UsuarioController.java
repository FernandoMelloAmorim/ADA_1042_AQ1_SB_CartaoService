package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CadastroUsuarioResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(path = "/cadastra", produces = "application/json" )
    public ResponseEntity<CadastroUsuarioResponse> cadastrarUsuario(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest){

        Usuario novoUsuario = usuarioService.cadastra(cadastroUsuarioRequest);

        CadastroUsuarioResponse response = mapUsuarioToCadastroUsuarioResponse(novoUsuario);
        log.info("Novo usuario cadastrado: {}", cadastroUsuarioRequest.getIdentificador());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaTodos() {
        return ResponseEntity.ok(usuarioService.listaTodos());
    }

    private CadastroUsuarioResponse mapUsuarioToCadastroUsuarioResponse(Usuario usuario) {

        CadastroUsuarioResponse response = new CadastroUsuarioResponse();

        response.setNumeroCartao(usuario.getIdentificador());
        response.setNomeTitularConta(usuario.getNome());

        return response;
    }

}
