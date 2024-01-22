package edu.school21.application.services;

import edu.school21.application.repositories.FunctionsRepository;
import edu.school21.application.services.helpers.SuggestionsValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FunctionsService {
    private final FunctionsRepository functionsRepository;
    private final SuggestionsValidator suggestionsValidator;

    @Getter
    private List lastResult;

    public List formationOfAverageTicketGrowth(
            final int workMode,
            final String startDate,
            final String endDate,
            final int numberTransactions,
            final double coefficientIncrease,
            final double maxChurnIndex,
            final double maxShareTransactionsDiscount,
            final double acceptableMarginShare
    ) {
        suggestionsValidator.validateNumberBetween("Режим работы", workMode, 1, 2);
        suggestionsValidator.validateDate(startDate, endDate);
        suggestionsValidator.validateNumberMin("Количество транзакций", numberTransactions, 0);
        suggestionsValidator.validatePercent("Максимальная доля транзакций", maxShareTransactionsDiscount);
        suggestionsValidator.validatePercent("Допустимая доля маржи", acceptableMarginShare);

        lastResult = functionsRepository.formationOfAverageTicketGrowth(
                workMode,
                startDate,
                endDate,
                numberTransactions,
                coefficientIncrease,
                maxChurnIndex,
                maxShareTransactionsDiscount,
                acceptableMarginShare
        );
        return this.lastResult;
    }

    public List formationOfIncreasingFrequencyOfVisits(
            final String startDate,
            final String endDate,
            final int numberTransactions,
            final double maxChurnIndex,
            final double maxShareTransactionsDiscount,
            final double acceptableMarginShare
    ) {
        suggestionsValidator.validateDate(startDate, endDate);
        suggestionsValidator.validateNumberMin("Количество транзакций", numberTransactions, 0);
        suggestionsValidator.validatePercent("Максимальная доля транзакций", maxShareTransactionsDiscount);
        suggestionsValidator.validatePercent("Допустимая доля маржи", acceptableMarginShare);

        this.lastResult = functionsRepository.formationOfIncreasingFrequencyOfVisits(
                startDate,
                endDate,
                numberTransactions,
                maxChurnIndex,
                maxShareTransactionsDiscount,
                acceptableMarginShare
        );
        return this.lastResult;
    }

    public List formationOfOffersFocusedOnCrossSelling(
            final int numberGroups,
            final double maxChurnIndex,
            final double maxConsumptionStabilityIndex,
            final double maxSkuSharePercentage,
            final double acceptableMarginShare
    ) {
        suggestionsValidator.validateNumberMin("Количество групп", numberGroups, 0);
        suggestionsValidator.validatePercent("Максимальная доля SKU", maxSkuSharePercentage);
        suggestionsValidator.validatePercent("Допустимая доля маржи", acceptableMarginShare);

        this.lastResult = functionsRepository.formationOfOffersFocusedOnCrossSelling(
                numberGroups,
                maxChurnIndex,
                maxConsumptionStabilityIndex,
                maxSkuSharePercentage,
                acceptableMarginShare
        );
        return this.lastResult;
    }
}
