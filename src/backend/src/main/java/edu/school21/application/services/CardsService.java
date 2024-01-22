package edu.school21.application.services;

import edu.school21.application.entities.CardEntity;
import edu.school21.application.graphql.models.CardModel;
import edu.school21.application.repositories.CardsRepository;
import edu.school21.application.services.converters.CardConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("cards")
@AllArgsConstructor
public class CardsService implements EduService<CardEntity, CardModel> {

    private CardsRepository repository;
    private CardConverter converter;

    @Override
    @Transactional
    public CardEntity created(final CardModel dto) {
        return repository.save(converter.dtoToEntity(dto)
        );
    }

    @Override
    @Transactional
    public CardEntity update(final CardModel dto) {
        return repository.save(converter.dtoToEntity(dto)
        );
    }

    @Override
    public List<CardModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "customerCardId")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public CardModel findById(final String id) {
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

