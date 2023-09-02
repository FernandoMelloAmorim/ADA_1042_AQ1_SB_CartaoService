package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface CompraService {

    Compra realizarCompra(Cartao numeroCartao, String loja, BigDecimal valor);

    CompletableFuture<CompraResponse> realizarCompraAsync(CompraRequest compraRequest);

}