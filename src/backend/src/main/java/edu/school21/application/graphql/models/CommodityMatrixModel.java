package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CommodityMatrixModel extends EduModel {
    private final String skuId;
    private final String skuName;
    private final String groupId;
}
