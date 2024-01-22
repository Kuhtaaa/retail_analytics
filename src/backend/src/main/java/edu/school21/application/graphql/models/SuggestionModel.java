package edu.school21.application.graphql.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Обобщенная модель результат функций персональных предложений
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuggestionModel {
    /**
     * Идентификатор клиента
     */
    private final int customerId;

    /**
     * Целевое значение среднего чека
     */
    private final Float requiredCheckMeasure;

    /**
     * Группа предложения
     */
    private final String offerGroup;

    /**
     * Дата начала периода
     */
    private final String startDate;

    /**
     * Дата окончания период
     */
    private final String endDate;

    /**
     * Целевое количество транзакций
     */
    private final Integer numberTransactions;

    /**
     * SKU предложения
     */
    private final String skuOffers;

    /**
     * Максимальная глубина скидки
     */
    private final double maxDiscountDepth;

    public static SuggestionModel suggestionForAverageTicketGrowth(
            final int customerId,
            final float requiredCheckMeasure,
            final String offerGroup,
            final float maxDiscountDepth
    ) {
        return new SuggestionModel(
                customerId,
                requiredCheckMeasure,
                offerGroup,
                null,
                null,
                null,
                null,
                maxDiscountDepth
        );
    }

    public static SuggestionModel suggestionForIncreasingFrequencyOfVisits(
            final int customerId,
            final String startDate,
            final String endDate,
            final int numberTransactions,
            final String offerGroup,
            final float maxDiscountDepth
    ) {
        return new SuggestionModel(
                customerId,
                null,
                offerGroup,
                startDate,
                endDate,
                numberTransactions,
                null,
                maxDiscountDepth
        );
    }

    public static SuggestionModel suggestionForOffersFocusedOnCrossSelling(
            final int customerId,
            final String skuOffers,
            final float maxDiscountDepth
    ) {
        return new SuggestionModel(
                customerId,
                null,
                null,
                null,
                null,
                null,
                skuOffers,
                maxDiscountDepth
        );
    }
}
