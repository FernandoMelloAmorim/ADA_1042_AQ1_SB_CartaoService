package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.FaturaFilter;

@Component
public class FaturaSpecification extends BaseSpecification<Fatura, FaturaFilter> {
    private Join<Fatura, Cartao> faturaCartaoJoin;

    @Override
    public Specification<Fatura> getFilter(FaturaFilter filter) {
        return null;
    }
}
