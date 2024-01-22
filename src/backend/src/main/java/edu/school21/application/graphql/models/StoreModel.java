package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class StoreModel extends EduModel {
    private final String transactionStoreId;
    private final String skuId;
    private final String skuPurchasePrice;
    private final String skuRetailPrice;
}
