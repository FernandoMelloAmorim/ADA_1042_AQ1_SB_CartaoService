package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService {

    @Override
    public void notificarOperadoraCompraCredito(Cartao cartao, BigDecimal valor) {

        String mensagem = String.format("Compra de R$ %.2f realizada com o cartão %s.", valor, cartao.getNumeroCartao());
        System.out.println(mensagem);
    }

}
