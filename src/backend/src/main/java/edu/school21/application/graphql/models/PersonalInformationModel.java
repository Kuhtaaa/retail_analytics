package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonalInformationModel extends EduModel {
    private final String customerId;
    private final String customerName;
    private final String customerSurname;
    private final String customerPrimaryEmail;
    private final String customerPrimaryPhone;
}
