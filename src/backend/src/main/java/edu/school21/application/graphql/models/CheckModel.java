package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckModel extends EduModel {
    private final String transactionId;
    private final String skuId;
    private final String skuAmount;
    private final String skuSumm;
    private final String skuSummPaid;
    private final String skuDiscount;
}
