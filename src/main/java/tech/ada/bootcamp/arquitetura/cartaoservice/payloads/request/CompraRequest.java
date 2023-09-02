package tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequest implements Serializable {

    @NotBlank
    private String numeroCartao;
    @NotBlank
    private String loja;
    @NotNull
    private double valor;

}
