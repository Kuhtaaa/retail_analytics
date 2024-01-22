import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { PersonalInformation } from "./types";

export type PersonalInformationQuery = {
  __typename?: "PersonalInformationQuery"
  personalInformation?: Maybe<PersonalInformationQueries>
}

export type PersonalInformationMutation = {
  __typename?: "PersonalInformationMutation"
  personalInformation?: Maybe<PersonalInformationMutations>
}

export type PersonalInformationQueries = {
  __typename?: "PersonalInformationQueries"
  getAllPersonalInformation: Array<PersonalInformation>
  getPersonalInformationById: PersonalInformation
}

export type PersonalInformationQueriesGetPersonalInformationByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type PersonalInformationMutations = {
  __typename?: "PersonalInformationMutations"
  createPersonalInformation?: Maybe<Scalars["Boolean"]>
  updatePersonalInformation?: Maybe<Scalars["Boolean"]>
  deletePersonalInformation?: Maybe<Scalars["Boolean"]>
}

export type PersonalInformationMutationsCreatePersonalInformationArgs = {
  customerId?: Maybe<Scalars["String"]>
  customerName?: Maybe<Scalars["String"]>
  customerSurname?: Maybe<Scalars["String"]>
  customerPrimaryEmail?: Maybe<Scalars["String"]>
  customerPrimaryPhone?: Maybe<Scalars["String"]>
}

export type PersonalInformationMutationsUpdatePersonalInformationArgs = {
  customerId?: Maybe<Scalars["String"]>
  customerName?: Maybe<Scalars["String"]>
  customerSurname?: Maybe<Scalars["String"]>
  customerPrimaryEmail?: Maybe<Scalars["String"]>
  customerPrimaryPhone?: Maybe<Scalars["String"]>
}

export type PersonalInformationMutationsDeletePersonalInformationArgs = {
  customerId?: Maybe<Scalars["String"]>
}
