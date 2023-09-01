package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listaTodos() {

        final List<Usuario> usuarios = this.usuarioRepository.findAll();

        return usuarios;
    }

    @Override
    public Usuario cadastra(CadastroUsuarioRequest cadastroUsuarioRequest) {

        Usuario novoUsuario = mapCadastroUsuarioRequestToUsuario(cadastroUsuarioRequest);

        return usuarioRepository.save(novoUsuario);
    }

    private Usuario mapCadastroUsuarioRequestToUsuario(CadastroUsuarioRequest request) {

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setIdentificador(request.getIdentificador());

        if (request.getTipoCartao() != null) {
            List<Cartao> cartoes = request.getTipoCartao().stream()
                    .map(tipoCartao -> criarCartao(tipoCartao, usuario))
                    .collect(Collectors.toList());

            usuario.setCartoes(cartoes);
        }

        return usuario;
    }

    private Cartao criarCartao(TipoCartao tipoCartao, Usuario usuario) {

        Cartao cartao = new Cartao();
        cartao.setTipoCartao(tipoCartao);
        cartao.setUsuario(usuario);

        return cartao;
    }

}