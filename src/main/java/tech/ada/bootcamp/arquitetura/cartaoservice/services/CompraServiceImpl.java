package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;
import tech.ada.bootcamp.arquitetura.cartaoservice.exception.CompraNaoEncontradaException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CompraRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CompraResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CompraRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CartaoService cartaoService;
    private final CompraRepository compraRepository;

    @Override
    public Compra realizarCompra(Cartao cartao, String loja, BigDecimal valor) {

        LocalDateTime dataAtual = LocalDateTime.now();

        Compra compra = new Compra();
        compra.setCartao(cartao);
        compra.setLoja(loja);
        compra.setValor(valor);
        compra.setDataCompra(dataAtual);
        compra.setStatusCompra(StatusCompra.PENDENTE);
        compra.setNumeroCartao(compra.getNumeroCartao());

        Compra compraRegistrada = compraRepository.save(compra);

        return compraRegistrada;
    }

    @Override
    public void atualizarStatusCompra(Compra compra, StatusCompra novoStatus) {

        compra.setStatusCompra(novoStatus);
        compraRepository.save(compra);
    }

    @Override
    public Compra obterUltimaCompraPorCartao(Cartao cartao) {
        return compraRepository.findTopByCartaoOrderByDataCompraDesc(cartao)
                .orElseThrow(() -> new CompraNaoEncontradaException(
                        "Compra não encontrada para o cartão: " + cartao.getNumeroCartao()));
    }

    @Override
    public List<Compra> obterTodasCompras() {
        return compraRepository.findAll();
    }

    @Override
    public CompletableFuture<CompraResponse> realizarCompraAsync(CompraRequest compraRequest) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                Cartao cartao = cartaoService.consultarPeloNumeroCartao(compraRequest.getNumeroCartao());

                // realiza a compra (simula operação demorada)
                Compra compra = realizarNotificacaoOperadora(cartao, BigDecimal.valueOf(compraRequest.getValor()));

                CompraResponse response = new CompraResponse();
                response.setNumeroCartao(cartao.getNumeroCartao());
                response.setLoja(compra.getLoja());
                response.setValor(compra.getValor().doubleValue());
                response.setStatusCompra(compra.getStatusCompra().name());

                return response;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao realizar a compra", e);
            }
        });
    }

    // simula a emissao de notificacao à operadora do cartao
    private Compra realizarNotificacaoOperadora(Cartao cartao, BigDecimal valor) {
        try {
            // simulando atraso de 5 segundos
            TimeUnit.SECONDS.sleep(5);

            // compra bem sucedida, retorna o status
            return new Compra(valor, StatusCompra.FINALIZADA);
        } catch (InterruptedException e) {
            // ou interrupção
            throw new RuntimeException("Erro na operação", e);
        }
    }

}