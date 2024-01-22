import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";

export type SuggestionQuery = {
  __typename?: "Query"
  suggestion?: Maybe<SuggestionQueries>
}

export type SuggestionQueries = {
  __typename?: "SuggestionQueries"
  formationOfAverageTicketGrowth: Array<Maybe<Suggestion>>
  formationOfIncreasingFrequencyOfVisits: Array<Maybe<Suggestion>>
  formationOfOffersFocusedOnCrossSelling?: Maybe<Array<Maybe<Suggestion>>>
}

export type SuggestionQueriesFormationOfAverageTicketGrowthArgs = {
  suggestionInput?: Maybe<SuggestionInput>
}

export type SuggestionQueriesFormationOfIncreasingFrequencyOfVisitsArgs = {
  suggestionInput?: Maybe<SuggestionInput>
}

export type SuggestionQueriesFormationOfOffersFocusedOnCrossSellingArgs = {
  suggestionInput?: Maybe<SuggestionInput>
}

export type Suggestion = {
  __typename?: "Suggestion"
  customerId: Scalars["Int"]
  requiredCheckMeasure?: Maybe<Scalars["Float"]>
  offerGroup?: Maybe<Scalars["String"]>
  startDate?: Maybe<Scalars["String"]>
  endDate?: Maybe<Scalars["String"]>
  numberTransactions?: Maybe<Scalars["Int"]>
  skuOffers?: Maybe<Scalars["String"]>
  maxDiscountDepth: Scalars["Float"]
}

export type SuggestionInput = {
  workMode?: Maybe<Scalars["Int"]>
  startDate?: Maybe<Scalars["String"]>
  endDate?: Maybe<Scalars["String"]>
  numberTransactions?: Maybe<Scalars["Int"]>
  coefficientIncrease?: Maybe<Scalars["Float"]>
  maxChurnIndex?: Maybe<Scalars["Float"]>
  maxShareTransactionsDiscount?: Maybe<Scalars["Float"]>
  acceptableMarginShare?: Maybe<Scalars["Float"]>
  maxSkuSharePercentage?: Maybe<Scalars["Float"]>
  numberGroups?: Maybe<Scalars["Int"]>
  maxConsumptionStabilityIndex?: Maybe<Scalars["Float"]>
}
