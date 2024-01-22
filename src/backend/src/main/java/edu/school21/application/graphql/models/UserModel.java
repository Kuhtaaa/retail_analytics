package edu.school21.application.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends EduModel {
    private final String id;
    private final String email;
    private final String name;
    private final String username;
}
