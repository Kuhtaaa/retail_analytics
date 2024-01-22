package edu.school21.application.graphql.resolvers;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.services.converters.SuggestionConverter;
import edu.school21.application.enums.Access;
import edu.school21.application.graphql.models.SuggestionInputModel;
import edu.school21.application.graphql.models.SuggestionModel;
import edu.school21.application.graphql.queries.SuggestionQueries;
import edu.school21.application.services.FunctionsService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class SuggestionQueriesResolver implements GraphQLResolver<SuggestionQueries> {

    private final FunctionsService functionsService;
    private final SuggestionConverter suggestionConverter;

    public List<SuggestionModel> formationOfAverageTicketGrowth(
            final SuggestionQueries suggestionQueries,
            final SuggestionInputModel suggestionInput
    ) {
        final List<SuggestionModel> result;
        try {
            result = suggestionConverter.convertSuggestionForAverageTicketGrowth(
                    functionsService.formationOfAverageTicketGrowth(
                            suggestionInput.getWorkMode(),
                            suggestionInput.getStartDate(),
                            suggestionInput.getEndDate(),
                            suggestionInput.getNumberTransactions(),
                            suggestionInput.getCoefficientIncrease(),
                            suggestionInput.getMaxChurnIndex(),
                            suggestionInput.getMaxShareTransactionsDiscount(),
                            suggestionInput.getAcceptableMarginShare()
                    )
            );
        } catch (final IllegalStateException e) {
            throw new ValidationGraphQLException(e.getMessage());
        }
        return result;
    }

    public List<SuggestionModel>  formationOfIncreasingFrequencyOfVisits(
            final SuggestionQueries suggestionQueries,
            final SuggestionInputModel suggestionInput
    ) {
        final List<SuggestionModel> result;
        try {
            result = suggestionConverter.convertSuggestionForIncreasingFrequencyOfVisits(
                    functionsService.formationOfIncreasingFrequencyOfVisits(
                            suggestionInput.getStartDate(),
                            suggestionInput.getEndDate(),
                            suggestionInput.getNumberTransactions(),
                            suggestionInput.getMaxChurnIndex(),
                            suggestionInput.getMaxShareTransactionsDiscount(),
                            suggestionInput.getAcceptableMarginShare()
                    )
            );
        } catch (final IllegalStateException e) {
            throw new ValidationGraphQLException(e.getMessage());
        }
        return result;
    }

    public List<SuggestionModel>  formationOfOffersFocusedOnCrossSelling(
            final SuggestionQueries suggestionQueries,
            final SuggestionInputModel suggestionInput
    ) {
        final List<SuggestionModel> result;
        try {
            result = suggestionConverter.convertSuggestionForOffersFocusedOnCrossSelling(
                    functionsService.formationOfOffersFocusedOnCrossSelling(
                            suggestionInput.getNumberGroups(),
                            suggestionInput.getMaxChurnIndex(),
                            suggestionInput.getMaxConsumptionStabilityIndex(),
                            suggestionInput.getMaxSkuSharePercentage(),
                            suggestionInput.getAcceptableMarginShare()
                    )
            );
        } catch (final IllegalStateException e) {
            throw new ValidationGraphQLException(e.getMessage());
        }
        return result;
    }
}
