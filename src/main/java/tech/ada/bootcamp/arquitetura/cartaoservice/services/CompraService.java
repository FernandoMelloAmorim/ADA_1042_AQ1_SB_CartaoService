package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CompraService {

    Compra realizarCompra(Cartao numeroCartao, String loja, BigDecimal valor);

    void atualizarStatusCompra(Compra compra, StatusCompra novoStatus);

    Compra obterUltimaCompraPorCartao(Cartao cartao);

    List<Compra> obterTodasCompras();

    CompletableFuture<CompraResponse> realizarCompraAsync(CompraRequest compraRequest);

}

//    Compra registrarCompra(Compra compra); // no banco
//    void processaCompra(Compra compra); // com operadora
//    Optional<Compra> buscarCompraPeloIdCompra(Long id);
////    Compra buscaCompraPeloUsuario(String idendtificador);
//    Page<Compra> listarCompras(Pageable pageable);