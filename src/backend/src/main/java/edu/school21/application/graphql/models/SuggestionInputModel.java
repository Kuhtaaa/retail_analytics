package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuggestionInputModel {

    /**
     * Метод расчета среднего чека (1 - за период, 2 - за количество)
     */
    private final Integer workMode;

    /**
     * Дата начала периода
     */
    private final String startDate;

    /**
     * Дата окончания период
     */
    private final String endDate;

    /**
     * Количество транзакций
     */
    private final Integer numberTransactions;

    /**
     * Коэффициент увеличения среднего чека
     */
    private final Double coefficientIncrease;

    /**
     * Максимальный индекс оттока
     */
    private final Double maxChurnIndex;

    /**
     * Максимальная доля транзакций со скидкой (в процентах)
     */
    private final Double maxShareTransactionsDiscount;

    /**
     * Допустимая доля маржи (в процентах)
     */
    private final Double acceptableMarginShare;

    /**
     * Максимальная доля SKU (в процентах)
     */
    private final Double maxSkuSharePercentage;

    /**
     * Количество групп
     */
    private final Integer numberGroups;

    /**
     * Максимальный индекс стабильности потребления
     */
    private final Double maxConsumptionStabilityIndex;
}
