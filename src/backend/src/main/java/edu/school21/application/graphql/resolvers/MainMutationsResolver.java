package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.mutations.*;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Главный резолвер мутаций (Mutations).
 * При добавлении новой схемы и создание типа, который наследует базовый тип Mutation, необходимо добавить его сюда.
 */
@Component
@AllArgsConstructor
public class MainMutationsResolver implements GraphQLMutationResolver {

    private final CardMutations cardMutations;
    private final CheckMutations checkMutations;
    private final StoreMutations storeMutations;
    private final SkuGroupMutations skuGroupMutations;
    private final TransactionMutations transactionMutations;
    private final CommodityMatrixMutations commodityMatrixMutations;
    private final PersonalInformationMutations personalInformationMutations;
    private final DateOfAnalysisFormationMutations dateOfAnalysisFormationMutations;

    public CardMutations card() {
        return cardMutations;
    }

    public StoreMutations store() {
        return storeMutations;
    }

    public SkuGroupMutations skuGroup() {
        return skuGroupMutations;
    }

    public TransactionMutations transaction() {
        return transactionMutations;
    }

    public CheckMutations check() {
        return checkMutations;
    }

    public CommodityMatrixMutations commodityMatrix() {
        return commodityMatrixMutations;
    }

    public DateOfAnalysisFormationMutations dateOfAnalysisFormation() {
        return dateOfAnalysisFormationMutations;
    }

    public PersonalInformationMutations personalInformation() {
        return personalInformationMutations;
    }
}
