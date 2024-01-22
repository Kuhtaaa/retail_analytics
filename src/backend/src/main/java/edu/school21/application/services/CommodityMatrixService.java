package edu.school21.application.services;

import edu.school21.application.entities.CommodityMatrixEntity;
import edu.school21.application.graphql.models.CommodityMatrixModel;
import edu.school21.application.repositories.CommodityMatrixRepository;
import edu.school21.application.services.converters.CommodityConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("commodity_matrix")
@AllArgsConstructor
public class CommodityMatrixService implements EduService<CommodityMatrixEntity, CommodityMatrixModel> {

    private CommodityMatrixRepository repository;
    private CommodityConverter converter;

    @Override
    @Transactional
    public CommodityMatrixEntity created(final CommodityMatrixModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    @Transactional
    public CommodityMatrixEntity update(final CommodityMatrixModel dto) {
        return repository.save(converter.dtoToEntity(dto));
    }

    @Override
    public List<CommodityMatrixModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "skuId")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public CommodityMatrixModel findById(final String id) {
        return converter.entityToDto(
                repository.findById(Long.parseLong(id))
                          .orElseThrow(RuntimeException::new)
        );
    }

    @Override
    @Transactional
    public void delete(final String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public boolean existsById(final String id) {
        return repository.existsById(Long.parseLong(id));
    }
}