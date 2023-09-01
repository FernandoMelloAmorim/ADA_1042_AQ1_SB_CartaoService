package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T, P> {

    public abstract Specification<T> getFilter(P filter);
}
