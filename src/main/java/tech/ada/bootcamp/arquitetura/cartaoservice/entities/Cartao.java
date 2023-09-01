package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String nomeTitular;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate vencimentoCartao;
    private String codigoSeguranca;

    private TipoCartao tipoCartao;

    private String idContaBanco;

    private Boolean dependente = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "usuarioIdentificador")
    @JsonIgnore // anotação para evitar a recursão infinita
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}