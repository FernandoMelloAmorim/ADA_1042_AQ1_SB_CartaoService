package tech.ada.bootcamp.arquitetura.cartaoservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    boolean existsByNumeroCartao(String numeroCartao);
    Cartao findByNumeroCartao(String numeroCartao);

}