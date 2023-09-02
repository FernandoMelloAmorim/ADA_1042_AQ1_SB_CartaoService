package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;

public interface CartaoService {

    Cartao criarCartao(CartaoRequest cartaoRequest, Usuario usuario);

    Cartao consultarPeloNumeroCartao(String numeroCartao);

}
