package edu.school21.application.services;

import edu.school21.application.entities.CheckEntity;
import edu.school21.application.entities.CheckEntityPK;
import edu.school21.application.graphql.models.CheckModel;
import edu.school21.application.repositories.ChecksRepository;
import edu.school21.application.services.converters.CheckConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("checks")
@AllArgsConstructor
public class CheckService {

    private ChecksRepository repository;
    private CheckConverter converter;

    @Transactional
    public CheckEntity created(final CheckModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Transactional
    public CheckEntity update(final CheckModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    public List<CheckModel> findAll() {
        return repository
                .findAll(Sort.by(
                        new Sort.Order(Sort.Direction.ASC, "transactionId"),
                        new Sort.Order(Sort.Direction.ASC, "skuId")
                ))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    public CheckModel findById(final String transactionId, final String skuId) {
        final CheckEntityPK pk = new CheckEntityPK(Long.parseLong(transactionId), Long.parseLong(skuId));
        return converter.entityToDto(
                repository.findById(pk)
                          .orElseThrow(RuntimeException::new)
        );
    }

    @Transactional
    public void delete(final String transactionId, final String skuId) {
        final CheckEntityPK pk = new CheckEntityPK(Long.parseLong(transactionId), Long.parseLong(skuId));
        repository.deleteById(pk);
    }

    public boolean existsById(final String transactionId, final String skuId) {
        final CheckEntityPK pk = new CheckEntityPK(Long.parseLong(transactionId), Long.parseLong(skuId));
        return repository.existsById(pk);
    }
}

