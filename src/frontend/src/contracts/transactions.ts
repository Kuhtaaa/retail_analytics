import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { Transaction } from "./types";

export type TransactionQuery = {
  __typename?: "TransactionQuery"
  transaction?: Maybe<TransactionQueries>
}

export type TransactionMutation = {
  __typename?: "TransactionMutation"
  transaction?: Maybe<TransactionMutations>
}

export type TransactionQueries = {
  __typename?: "TransactionQueries"
  getAllTransactions: Array<Transaction>
  getTransactionById: Transaction
  getTransactionEntitiesBetweenDate: Array<Maybe<Transaction>>
}

export type TransactionQueriesGetTransactionByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type TransactionQueriesGetTransactionEntitiesBetweenDateArgs = {
  dateTimeBegin?: Maybe<Scalars["String"]>
  dateTimeEng?: Maybe<Scalars["String"]>
}

export type TransactionMutations = {
  __typename?: "TransactionMutations"
  createTransaction?: Maybe<Scalars["Boolean"]>
  updateTransaction?: Maybe<Scalars["Boolean"]>
  deleteTransaction?: Maybe<Scalars["Boolean"]>
}

export type TransactionMutationsCreateTransactionArgs = {
  transactionId?: Maybe<Scalars["String"]>
  customerCardId?: Maybe<Scalars["String"]>
  transactionSumm?: Maybe<Scalars["String"]>
  transactionDatetime?: Maybe<Scalars["String"]>
  transactionStoreId?: Maybe<Scalars["String"]>
}

export type TransactionMutationsUpdateTransactionArgs = {
  transactionId?: Maybe<Scalars["String"]>
  customerCardId?: Maybe<Scalars["String"]>
  transactionSumm?: Maybe<Scalars["String"]>
  transactionDatetime?: Maybe<Scalars["String"]>
  transactionStoreId?: Maybe<Scalars["String"]>
}

export type TransactionMutationsDeleteTransactionArgs = {
  transactionId?: Maybe<Scalars["String"]>
}
