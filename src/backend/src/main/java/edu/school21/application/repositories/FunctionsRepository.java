package edu.school21.application.repositories;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FunctionsRepository {

    private static final String AVERAGE_TICKET_GROWTH =
            "SELECT * FROM average_ticket_growth(" +
                    ":workMode, " +
                    ":startDate, " +
                    ":endDate, " +
                    ":numberTransactions, " +
                    ":coefficientIncrease, " +
                    ":maxChurnIndex, " +
                    ":maxShareTransactionsDiscount, " +
                    ":acceptableMarginShare)";

    private static final String INCREASING_FREQUENCY_OF_VISITS =
            "SELECT * FROM increasing_the_frequency_of_visits(" +
                    ":startDate, " +
                    ":endDate, " +
                    ":numberTransactions, " +
                    ":maxChurnIndex, " +
                    ":maxShareTransactionsDiscount, " +
                    ":acceptableMarginShare)";

    private static final String OFFERS_FOCUSED_ON_CROSS_SELLING =
            "SELECT * FROM offers_focused_on_cross_selling(" +
                    ":numberGroups, " +
                    ":maxChurnIndex, " +
                    ":maxConsumptionStabilityIndex, " +
                    ":maxSkuSharePercentage, " +
                    ":acceptableMarginShare)";

    private final EntityManager entityManager;

    /**
     * Формирование персональных предложений, ориентированных на рост среднего чека
     *
     * @param workMode                     метод расчета среднего чека (1 - за период, 2 - за количество)
     * @param startDate                    стартовая дата периода (для 1 метода)
     * @param endDate                      конечная дата периода (для 1 метода)
     * @param numberTransactions           количество транзакций (для 2 метода)
     * @param coefficientIncrease          коэффициент увеличения среднего чека
     * @param maxChurnIndex                максимальный индекс оттока
     * @param maxShareTransactionsDiscount максимальная доля транзакций со скидкой (в процентах)
     * @param acceptableMarginShare        допустимая доля маржи (в процентах)
     * @return List<' Object [ ] '>, где Object[] - одна строка результирующей таблицы
     */
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
        return entityManager.createNativeQuery(AVERAGE_TICKET_GROWTH)
                .setParameter("workMode", workMode)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("numberTransactions", numberTransactions)
                .setParameter("coefficientIncrease", coefficientIncrease)
                .setParameter("maxChurnIndex", maxChurnIndex)
                .setParameter("maxShareTransactionsDiscount", maxShareTransactionsDiscount)
                .setParameter("acceptableMarginShare", acceptableMarginShare)
                .getResultList();
    }

    /**
     * Формирование персональных предложений, ориентированных на рост частоты визитов
     *
     * @param startDate                     первая дата периода
     * @param endDate                       последняя дата периода
     * @param numberTransactions            добавляемое число транзакций
     * @param maxChurnIndex                 максимальный индекс оттока
     * @param maxShareTransactionsDiscount  максимальная доля транзакций со скидкой (в процентах)
     * @param acceptableMarginShare         допустимая доля маржи (в процентах)
     * @return List<'Object[]'>, где Object[] - одна строка результирующей таблицы
     */
    public List formationOfIncreasingFrequencyOfVisits(
            final String startDate,
            final String endDate,
            final int numberTransactions,
            final double maxChurnIndex,
            final double maxShareTransactionsDiscount,
            final double acceptableMarginShare
    ) {
        return entityManager.createNativeQuery(INCREASING_FREQUENCY_OF_VISITS)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("numberTransactions", numberTransactions)
                .setParameter("maxChurnIndex", maxChurnIndex)
                .setParameter("maxShareTransactionsDiscount", maxShareTransactionsDiscount)
                .setParameter("acceptableMarginShare", acceptableMarginShare)
                .getResultList();
    }

    /**
     * Формирование персональных предложений, ориентированных на кросс-продажи
     *
     * @param numberGroups                  количество групп
     * @param maxChurnIndex                 максимальный индекс оттока
     * @param maxConsumptionStabilityIndex  максимальный индекс стабильности потребления
     * @param maxSkuSharePercentage         максимальная доля SKU (в процентах)
     * @param acceptableMarginShare         допустимая доля маржи (в процентах)
     * @return List<'Object[]'>, где Object[] - одна строка результирующей таблицы
     */
    public List formationOfOffersFocusedOnCrossSelling(
            final int numberGroups,
            final double maxChurnIndex,
            final double maxConsumptionStabilityIndex,
            final double maxSkuSharePercentage,
            final double acceptableMarginShare
    ) {
        return entityManager.createNativeQuery(OFFERS_FOCUSED_ON_CROSS_SELLING)
                .setParameter("numberGroups", numberGroups)
                .setParameter("maxChurnIndex", maxChurnIndex)
                .setParameter("maxConsumptionStabilityIndex", maxConsumptionStabilityIndex)
                .setParameter("maxSkuSharePercentage", maxSkuSharePercentage)
                .setParameter("acceptableMarginShare", acceptableMarginShare)
                .getResultList();
    }
}
