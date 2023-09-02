package tech.ada.bootcamp.arquitetura.cartaoservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dataCompra;

    public BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "numeroCartao")
    public Cartao cartao;

    @Transient
    public String numeroCartao;

    public String loja;

    public StatusCompra statusCompra;

    public Compra(BigDecimal valor, StatusCompra statusCompra) {
        this.valor = valor;
        this.statusCompra = statusCompra;
    }

}