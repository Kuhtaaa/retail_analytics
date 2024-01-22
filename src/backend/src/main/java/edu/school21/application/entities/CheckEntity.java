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
@Table(name = "checks", schema = "public", catalog = "retail21java")
@IdClass(CheckEntityPK.class)
public class CheckEntity extends EduEntity {
    @Id
    @Column(name = "transaction_id")
    private long transactionId;
    @Id
    @Column(name = "sku_id")
    private long skuId;
    @Basic
    @Column(name = "sku_amount")
    private long skuAmount;
    @Basic
    @Column(name = "sku_summ")
    private long skuSumm;
    @Basic
    @Column(name = "sku_summ_paid")
    private long skuSummPaid;
    @Basic
    @Column(name = "sku_discount")
    private long skuDiscount;
}
