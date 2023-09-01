//package tech.ada.bootcamp.arquitetura.cartaoservice.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
//import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
//import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CriarNovoCartaoService {
//    private final CartaoRepository cartaoRepository;
//
//        cartao.setTipoCartao(cadastroUsuarioRequest.getTipoCartao());
//        Usuario usuario =  new Usuario();
//        usuario.setIdentificador(cadastroUsuarioRequest.getIdentificador());
//        cartao.setUsuario(usuario);
//        cartao.setIdContaBanco(UUID.randomUUID().toString());
//        cartao.setNomeTitular(cadastroUsuarioRequest.getNome());
//        cartao.setVencimentoCartao(dataAtual.plusYears(5));
//        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
//        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
//        return cartaoRepository.save(cartao);
//    }
//
//    private String gerarNumeroAleatorio(int length) {
//
//        final int tamanho = length<=0?1:length;
//        IntStream stream =  getRandom().ints(tamanho,0,9);
//        StringBuilder builder = new StringBuilder();
//        stream.forEachOrdered(builder::append);
//        return builder.toString();
//    }
//
//    private static Random getRandom(){
//        if(Objects.isNull(random)){
//            random = new Random();
//        }
//        return random;
//    }
//}
