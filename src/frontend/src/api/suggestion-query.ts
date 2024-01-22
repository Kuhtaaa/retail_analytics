import { gql } from '@apollo/client';

export const FormationOfAverageTicketGrowth = gql`
    query formationOfAverageTicketGrowth($suggestionInput: SuggestionInput) {
        suggestion {
            formationOfAverageTicketGrowth(suggestionInput: $suggestionInput) {
                customerId
                requiredCheckMeasure
                offerGroup
                maxDiscountDepth
            }
        }
    }
`;

export const FormationOfIncreasingFrequencyOfVisits = gql`
    query formationOfIncreasingFrequencyOfVisits($suggestionInput: SuggestionInput) {
        suggestion {
            formationOfIncreasingFrequencyOfVisits(suggestionInput: $suggestionInput) {
                customerId
                startDate
                endDate
                numberTransactions
                offerGroup
                maxDiscountDepth
            }
        }
    }
`;

export const FormationOfOffersFocusedOnCrossSelling = gql`
    query formationOfOffersFocusedOnCrossSelling($suggestionInput: SuggestionInput) {
        suggestion {
            formationOfOffersFocusedOnCrossSelling(suggestionInput: $suggestionInput) {
                customerId
                skuOffers
                maxDiscountDepth
            }
        }
    }
`;
