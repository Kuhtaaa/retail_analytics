import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { Card } from "./types";

export type CardQuery = {
  __typename?: "CardMutation"
  card?: Maybe<CardQueries>
}

export type CardMutation = {
  __typename?: "CardMutation"
  card?: Maybe<CardMutations>
}

export type CardQueries = {
  __typename?: "CardQueries"
  getAllCards: Array<Card>
  getCardById: Card
}

export type CardQueriesGetCardByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type CardMutations = {
  __typename?: "CardMutations"
  createCard?: Maybe<Scalars["Boolean"]>
  updateCard?: Maybe<Scalars["Boolean"]>
  deleteCard?: Maybe<Scalars["Boolean"]>
}

export type CardMutationsCreateCardArgs = {
  customerCardId?: Maybe<Scalars["String"]>
  customerId?: Maybe<Scalars["String"]>
}

export type CardMutationsUpdateCardArgs = {
  customerCardId?: Maybe<Scalars["String"]>
  customerId?: Maybe<Scalars["String"]>
}

export type CardMutationsDeleteCardArgs = {
  customerCardId?: Maybe<Scalars["String"]>
}
