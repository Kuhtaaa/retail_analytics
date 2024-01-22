package edu.school21.application.graphql.mutations;

import edu.school21.application.enums.InfoMessages;
import edu.school21.application.exceptions.ValidationGraphQLException;
import edu.school21.application.graphql.models.SkuGroupModel;
import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.services.SkuGroupsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@AccessControl(Access.PRIVATE)
public class SkuGroupMutations {

    private SkuGroupsService service;

    public boolean createSkuGroups(final String groupName) {
        service.created(new SkuGroupModel("0", groupName));
        return true;
    }

    public boolean updateSkuGroups(
            final String groupId,
            final String groupName
    ) {
        service.update(new SkuGroupModel(groupId, groupName));
        return true;
    }

    public boolean deleteSkuGroups(final String groupId) {
        try {
            service.delete(groupId);
        } catch (final Exception e) {
            throw new ValidationGraphQLException(InfoMessages.DELETION_ERROR.getName());
        }
        return true;
    }
}
