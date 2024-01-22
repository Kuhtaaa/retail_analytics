package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.models.CardModel;
import edu.school21.application.graphql.queries.CardQueries;
import edu.school21.application.services.CardsService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CardQueriesResolver implements GraphQLResolver<CardQueries> {

    private CardsService service;

    public List<CardModel> getAllCards(final CardQueries cardQueries) {
        return service.findAll();
    }

    public CardModel getCardById(
            final CardQueries cardQueries,
            final String id
    ) {
        return service.findById(id);
    }
}
