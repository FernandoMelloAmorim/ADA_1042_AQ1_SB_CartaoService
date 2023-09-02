package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

import java.math.BigDecimal;

public interface NotificacaoService {

    void notificarOperadoraCompraCredito(Cartao cartao, BigDecimal valor);

}
