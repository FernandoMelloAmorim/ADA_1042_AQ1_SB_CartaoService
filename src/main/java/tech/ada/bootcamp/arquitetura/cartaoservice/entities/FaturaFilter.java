package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaturaFilter {
    private Long id;
    private LocalDate dataProcessamento;
    private String numeroCartao;
}
