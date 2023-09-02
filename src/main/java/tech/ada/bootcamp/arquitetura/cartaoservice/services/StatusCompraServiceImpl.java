package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Compra;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.StatusCompra;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusCompraServiceImpl implements StatusCompraService {

    private final CompraService compraService;

    @Override
    public void atualizarStatusCompra(Cartao cartao) {

        Compra compra = compraService.obterUltimaCompraPorCartao(cartao);
        log.info("Status da compra realizada com o cartão {} está {}.", cartao.getNumeroCartao(), compra.getStatusCompra());

        compraService.atualizarStatusCompra(compra, StatusCompra.FINALIZADA);
        log.info("Status da compra realizada com o cartão {} atualizado para {}.", cartao.getNumeroCartao(), compra.getStatusCompra());
    }

}
