package edu.school21.application.graphql.resolvers;

import edu.school21.application.graphql.queries.*;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MainQueriesResolver implements GraphQLQueryResolver {
    private final UserQueries userQueries;
    private final CardQueries cardQueries;
    private final CheckQueries checkQueries;
    private final StoreQueries storeQueries;
    private final SkuGroupQueries skuGroupQueries;
    private final SuggestionQueries suggestionQueries;
    private final TransactionQueries transactionQueries;
    private final CommodityMatrixQueries commodityMatrixQueries;
    private final PersonalInformationQueries personalInformationQueries;
    private final DateOfAnalysisFormationQueries dateOfAnalysisFormationQueries;

    public CardQueries card() {
        return cardQueries;
    }

    public StoreQueries store() {
        return storeQueries;
    }

    public SkuGroupQueries skuGroup() {
        return skuGroupQueries;
    }

    public TransactionQueries transaction() {
        return transactionQueries;
    }

    public SuggestionQueries suggestion() {
        return suggestionQueries;
    }

    public CheckQueries check() {
        return checkQueries;
    }

    public PersonalInformationQueries personalInformation() {
        return personalInformationQueries;
    }

    public CommodityMatrixQueries commodityMatrix() {
        return commodityMatrixQueries;
    }

    public DateOfAnalysisFormationQueries dateOfAnalysisFormation() {
        return dateOfAnalysisFormationQueries;
    }

    public UserQueries user() {
        return userQueries;
    }

}
