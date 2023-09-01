package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Fatura;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.FaturaFilter;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.FaturaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaturaService {
    private final FaturaRepository faturaRepository;
    private final FaturaSpecification faturaSpecification;

    public Fatura getLastFatura(){
        FaturaFilter filter = FaturaFilter.builder().build();
        return faturaRepository.findOne(faturaSpecification.getFilter(filter)).orElseThrow(
                () ->
                        new ChangeSetPersister.NotFoundException(
                                String.format("Incident with ID [%d] not found.", filter.getId().get(0))));;
    }

    public List<Fatura> getAllFatura(){
        return faturaRepository.findAll();
    }
}
