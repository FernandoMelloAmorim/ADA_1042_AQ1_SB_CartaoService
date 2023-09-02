package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CartaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.UsuarioService;

@RestController
@RequestMapping("/cartao-credito")
@Slf4j
@RequiredArgsConstructor
public class CartaoCreditoController {

    private final CartaoService cartaoService;
    private final UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<Cartao> adicionaNovoCartao(@RequestBody CartaoRequest cartaoRequest) {

        Usuario usuario = usuarioService.buscarUsuarioPorIdentificador(cartaoRequest.getUsuarioIdentificador());

        Cartao cartao = cartaoService.criarCartao(cartaoRequest, usuario);
        log.info("Criado cartao de numero: {} para ususario: {}", cartaoRequest.getNumeroCartao(), usuario.getIdentificador());

        return new ResponseEntity<>(cartao, HttpStatus.CREATED);
    }

}
