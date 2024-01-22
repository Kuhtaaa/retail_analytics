package edu.school21.application.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commodity_matrix", schema = "public", catalog = "retail21java")
public class CommodityMatrixEntity extends EduEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sku_id", nullable = false)
    private long skuId;
    @Basic
    @Column(name = "sku_name", nullable = false, length = -1)
    private String skuName;
    @Basic
    @Column(name = "group_id", nullable = false)
    private long groupId;
}
