package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;

@Data
public class CartaoRequest {

    private String numeroCartao;
    private String nomeTitular;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;
    private TipoCartao tipoCartao;
    private String idContaBanco;
    private String usuarioIdentificador;

}
