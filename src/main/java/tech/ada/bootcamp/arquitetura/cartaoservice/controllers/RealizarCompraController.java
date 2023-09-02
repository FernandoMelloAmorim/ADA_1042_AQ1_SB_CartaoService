package tech.ada.bootcamp.arquitetura.cartaoservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import tech.ada.bootcamp.arquitetura.cartaoservice.services.NotificacaoService;
import tech.ada.bootcamp.arquitetura.cartaoservice.services.StatusCompraService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/compra")
@Slf4j
@RequiredArgsConstructor
public class RealizarCompraController {

    private final CompraService compraService;
    private final CartaoRepository cartaoRepository;
    private final NotificacaoService notificacaoService;
    private final StatusCompraService statusCompraService;

    @PostMapping(path = "/cartao-credito", produces = "application/json" )
    public ResponseEntity<CompraResponse> realizarCompra(@RequestBody CompraRequest compraRequest) throws Exception, CartaoNaoEncontradoException {

        log.info("Checando request com o cartao de numero: {}", compraRequest.getNumeroCartao());

        Cartao cartao = null;
        cartao = cartaoRepository.findByNumeroCartao(compraRequest.getNumeroCartao());
//                    .orElseThrow(() -> new CartaoNaoEncontradoException("Cartão não encontrado"));

        Compra compra = compraService.realizarCompra(cartao, compraRequest.getLoja(),
                BigDecimal.valueOf(compraRequest.getValor()));

        // simula emitir notificação à operadora de crédito após a compra
        notificacaoService.notificarOperadoraCompraCredito(cartao, compra.getValor());

        statusCompraService.atualizarStatusCompra(cartao);

        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setNumeroCartao(cartao.getNumeroCartao());
        compraResponse.setLoja(compra.getLoja());
        compraResponse.setValor(compra.getValor().doubleValue());
        compraResponse.setStatusCompra(compra.getStatusCompra().name());

        return ResponseEntity.ok(compraResponse);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Compra>> obterCompras() {

        List<Compra> lista = compraService.obterTodasCompras();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ultima-compra/{numeroCartao}")
    public ResponseEntity<Compra> obterUltimaCompra(@PathVariable String numeroCartao) {

        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        Compra ultimaCompra = compraService.obterUltimaCompraPorCartao(cartao);

        return ResponseEntity.ok(ultimaCompra);
    }

}
