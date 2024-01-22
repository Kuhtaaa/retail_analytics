package edu.school21.application.services;

import edu.school21.application.entities.TransactionEntity;
import edu.school21.application.graphql.models.TransactionModel;
import edu.school21.application.repositories.TransactionsRepository;
import edu.school21.application.services.converters.TransactionsConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service("transactions")
@AllArgsConstructor
public class TransactionsService implements EduService<TransactionEntity, TransactionModel> {
    private TransactionsRepository repository;
    private TransactionsConverter converter;

    @Override
    @Transactional
    public TransactionEntity created(final TransactionModel dto) {
        return repository.save(
                converter.dtoToEntity(dto)
        );
    }

    @Override
    @Transactional
    public TransactionEntity update(final TransactionModel dto) {
        return repository.save(
                converter.dtoToEntity(dto)
        );
    }

    @Override
    public List<TransactionModel> findAll() {
        return repository
                .findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "transactionId")))
                .stream()
                .map(entity -> converter.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionModel findById(final String id) {
        return converter.entityToDto(
                repository.findById(Long.parseLong(id))
                          .orElseThrow(RuntimeException::new)
        );
    }

    @Transactional
    public List<TransactionModel> findTransactionEntitiesBetweenDate(
            final String dateTimeBegin,
            final String dateTimeEnd
    ) {
        return repository
                .findTransactionEntitiesBetweenDate(
                        Timestamp.valueOf(dateTimeBegin),
                        Timestamp.valueOf(dateTimeEnd))
                .stream()
                .map(t -> converter.entityToDto(t))
                .collect(Collectors.toList());
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