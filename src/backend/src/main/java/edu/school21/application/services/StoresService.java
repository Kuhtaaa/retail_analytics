package edu.school21.application.services;

import edu.school21.application.entities.StoreEntity;
import edu.school21.application.entities.StoreEntityPK;
import edu.school21.application.graphql.models.StoreModel;
import edu.school21.application.repositories.StoresRepository;
import edu.school21.application.services.converters.StoreConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("stores")
@AllArgsConstructor
public class StoresService {
    private StoresRepository repository;
    private StoreConverter converter;

    @Transactional
    public StoreEntity created(final StoreModel dto) {
        return repository.save(
                converter.dtoToEntity(dto)
        );
    }

    @Transactional
    public StoreEntity update(final StoreModel dto) {
        return repository.save(
                converter.dtoToEntity(dto)
        );
    }

    public List<StoreModel> findAll() {
        return repository
                .findAll(Sort.by(
                        new Sort.Order(Sort.Direction.ASC, "transactionStoreId"),
                        new Sort.Order(Sort.Direction.ASC, "skuId")
                ))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    public StoreModel findById(final String transactionStoreId, final String skuId) {
        final StoreEntityPK pk = new StoreEntityPK(Long.parseLong(transactionStoreId), Long.parseLong(skuId));
        final StoreEntity entity = repository.findById(pk)
                                             .orElseThrow(RuntimeException::new);
        return converter.entityToDto(entity);
    }

    @Transactional
    public void delete(final String transactionStoreId, final String skuId) {
        final StoreEntityPK pk = new StoreEntityPK(Long.parseLong(transactionStoreId), Long.parseLong(skuId));
        repository.deleteById(pk);
    }

    public boolean existsById(final String transactionStoreId, final String skuId) {
        final StoreEntityPK pk = new StoreEntityPK(Long.parseLong(transactionStoreId), Long.parseLong(skuId));
        return repository.existsById(pk);
    }
}