package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.util.List;

@Data
@AllArgsConstructor
public class CadastroUsuarioRequest {
    private String identificador;

    private String nome;
//    private EnderecoRequest enderecoRequest;

    private List<TipoCartao> tipoCartao;

//    private List<String> dependentes;

}
