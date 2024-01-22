package edu.school21.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntityPK implements Serializable {
    @Column(name = "transaction_store_id")
    @Id
    private long transactionStoreId;
    @Column(name = "sku_id")
    @Id
    private long skuId;
}
