package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.exception.CartaoNaoEncontradoException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.CompraService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/compra")
@Slf4j
@RequiredArgsConstructor
public class RealizarCompraController {

    private final CompraService compraService;
    private final CartaoRepository cartaoRepository;

    @PostMapping(path = "/cartao-credito", produces = "application/json" )
    public ResponseEntity<CompraResponse> realizarCompra(@RequestBody CompraRequest compraRequest) throws Exception {

        log.info("Checando request com o cartao de numero: {}", compraRequest.getNumeroCartao());

        Cartao cartao = null;
        try {
            cartao = cartaoRepository.findByNumeroCartao(compraRequest.getNumeroCartao())
                    .orElseThrow(() -> new CartaoNaoEncontradoException("Cartão não encontrado"));
        } catch (CartaoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        Compra compra = compraService.realizarCompra(cartao, compraRequest.getLoja(),
                BigDecimal.valueOf(compraRequest.getValor()));

        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setNumeroCartao(cartao.getNumeroCartao());
        compraResponse.setLoja(compra.getLoja());
        compraResponse.setValor(compra.getValor().doubleValue());
        compraResponse.setStatusCompra(compra.getStatusCompra().name());

        return ResponseEntity.ok(compraResponse);

    }

}
