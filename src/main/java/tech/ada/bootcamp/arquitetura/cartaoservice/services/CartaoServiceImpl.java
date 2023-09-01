package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exception.CartaoJaExisteException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Cartao criarCartao(CartaoRequest cartaoRequest, Usuario usuario) {

//        if (cartaoNovo.getUsuario() == null) {
//            throw new UsuarioNaoEncontradoException("Usuário não fornecido para criar o cartão");
//        }
//
//        Usuario usuario = usuarioRepository.findByIdentificador(cartaoNovo.getUsuario().getIdentificador())
//                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado na base de dados: " + cartaoNovo.getUsuario().getIdentificador()));

//        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumeroCartao(cartaoNovo.getNumeroCartao());
//        if (cartaoOptional.isPresent()) {
//            throw new CartaoJaExisteException("Cartão já existe: " + cartaoNovo.getNumeroCartao());
//        }


        if (cartaoRepository.existsByNumeroCartao(cartaoRequest.getNumeroCartao())) {
            throw new CartaoJaExisteException("Cartão já existe: " + cartaoRequest.getNumeroCartao());
        }

        Cartao cartaoNovo = new Cartao();
        cartaoNovo.setNumeroCartao(cartaoRequest.getNumeroCartao());
        cartaoNovo.setNomeTitular(cartaoRequest.getNomeTitular());
        cartaoNovo.setVencimentoCartao(cartaoRequest.getVencimentoCartao());
        cartaoNovo.setCodigoSeguranca(cartaoRequest.getCodigoSeguranca());
        cartaoNovo.setTipoCartao(cartaoRequest.getTipoCartao());
        cartaoNovo.setIdContaBanco(cartaoRequest.getIdContaBanco());
        cartaoNovo.setUsuario(usuario);

        return cartaoRepository.save(cartaoNovo);

    }

    @Override
    public Cartao consultarCartaoPeloNumeroCartao(String numeroCartao) {
        return null;
    }

}