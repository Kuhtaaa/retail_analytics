package edu.school21.application.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores", schema = "public", catalog = "retail21java")
@IdClass(StoreEntityPK.class)
public class StoreEntity {
    @Id
    @Column(name = "transaction_store_id")
    private long transactionStoreId;
    @Id
    @Column(name = "sku_id")
    private long skuId;
    @Basic
    @Column(name = "sku_purchase_price")
    private double skuPurchasePrice;
    @Basic
    @Column(name = "sku_retail_price")
    private double skuRetailPrice;
}
