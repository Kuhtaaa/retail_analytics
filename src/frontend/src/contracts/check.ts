import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { Check } from "./types";

export type CheckQuery = {
  __typename?: "CheckQuery"
  check?: Maybe<CheckQueries>
}

export type CheckMutation = {
  __typename?: "CheckMutation"
  check?: Maybe<CheckMutations>
}

export type CheckQueries = {
  __typename?: "CheckQueries"
  getAllChecks: Array<Check>
  getCheckById: Check
}

export type CheckQueriesGetCheckByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type CheckMutations = {
  __typename?: "CheckMutations"
  createCheck?: Maybe<Scalars["Boolean"]>
  updateCheck?: Maybe<Scalars["Boolean"]>
  deleteCheck?: Maybe<Scalars["Boolean"]>
}

export type CheckMutationsCreateCheckArgs = {
  transactionId?: Maybe<Scalars["String"]>
  skuId?: Maybe<Scalars["String"]>
  skuAmount?: Maybe<Scalars["String"]>
  skuSumm?: Maybe<Scalars["String"]>
  skuSummPaid?: Maybe<Scalars["String"]>
  skuDiscount?: Maybe<Scalars["String"]>
}

export type CheckMutationsUpdateCheckArgs = {
  transactionId?: Maybe<Scalars["String"]>
  skuId?: Maybe<Scalars["String"]>
  skuAmount?: Maybe<Scalars["String"]>
  skuSumm?: Maybe<Scalars["String"]>
  skuSummPaid?: Maybe<Scalars["String"]>
  skuDiscoun?: Maybe<Scalars["String"]>
}

export type CheckMutationsDeleteCheckArgs = {
  transactionId?: Maybe<Scalars["String"]>
}
