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
@Table(name = "personal_information", schema = "public", catalog = "retail21java")
public class PersonalInformationEntity extends EduEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id", nullable = false)
    private long customerId;
    @Basic
    @Column(name = "customer_name", nullable = false, length = -1)
    private String customerName;
    @Basic
    @Column(name = "customer_surname", nullable = false, length = -1)
    private String customerSurname;
    @Basic
    @Column(name = "customer_primary_email", nullable = false, length = -1)
    private String customerPrimaryEmail;
    @Basic
    @Column(name = "customer_primary_phone", nullable = false, length = -1)
    private String customerPrimaryPhone;
}
