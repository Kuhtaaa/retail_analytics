import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { DateOfAnalysisFormation } from "./types";

export type DateOfAnalysisFormationQuery = {
  __typename?: "DateOfAnalysisFormationQuery"
  dateOfAnalysisFormation?: Maybe<DateOfAnalysisFormationQueries>
}

export type DateOfAnalysisFormationMutation = {
  __typename?: "DateOfAnalysisFormationMutation"
  dateOfAnalysisFormation?: Maybe<DateOfAnalysisFormationMutations>
}

export type DateOfAnalysisFormationQueries = {
  __typename?: "DateOfAnalysisFormationQueries"
  getAllDateOfAnalysisFormation: Array<DateOfAnalysisFormation>
  getDateOfAnalysisFormationById: DateOfAnalysisFormation
}

export type DateOfAnalysisFormationQueriesGetDateOfAnalysisFormationByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type DateOfAnalysisFormationMutations = {
  __typename?: "DateOfAnalysisFormationMutations"
  createDateOfAnalysisFormation?: Maybe<Scalars["Boolean"]>
  updateDateOfAnalysisFormation?: Maybe<Scalars["Boolean"]>
  deleteDateOfAnalysisFormation?: Maybe<Scalars["Boolean"]>
}

export type DateOfAnalysisFormationMutationsCreateDateOfAnalysisFormationArgs = {
  id?: Maybe<Scalars["String"]>
  analysisFormation?: Maybe<Scalars["String"]>
}

export type DateOfAnalysisFormationMutationsUpdateDateOfAnalysisFormationArgs = {
  id?: Maybe<Scalars["String"]>
  analysisFormation?: Maybe<Scalars["String"]>
}

export type DateOfAnalysisFormationMutationsDeleteDateOfAnalysisFormationArgs = {
  id?: Maybe<Scalars["String"]>
}
