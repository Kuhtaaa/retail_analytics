package edu.school21.application.graphql.resolvers;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.entities.RolesEntity;
import edu.school21.application.enums.Access;
import edu.school21.application.graphql.models.UserModel;
import edu.school21.application.graphql.queries.UserQueries;
import edu.school21.application.services.CustomerUserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserQueriesResolver implements GraphQLResolver<UserQueries> {

    private CustomerUserService service;

    @AccessControl(Access.PUBLIC)
    public List<String> getCurrentUserRoles(final UserQueries userQueries) {
        return service.getCurrentUserRoles()
                      .stream()
                      .map(RolesEntity::getName)
                      .collect(Collectors.toList());
    }

    @AccessControl(Access.PUBLIC)
    public boolean isAdmin(
            final UserQueries userQueries
    ) {
        return service.getAccessType() == Access.PRIVATE;
    }

    @AccessControl(Access.PRIVATE)
    public UserModel loadUserByUsername (
            final UserQueries userQueries,
            final String userNameOrEmail
    ) {
        return service.loadUserByUserAuthName(userNameOrEmail);
    }
}
