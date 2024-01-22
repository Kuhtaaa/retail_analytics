import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { Store } from "./types";

export type StoreQuery = {
  __typename?: "StoreQuery"
  store?: Maybe<StoreQueries>
}

export type StoreMutation = {
  __typename?: "StoreMutation"
  store?: Maybe<StoreMutations>
}

export type StoreQueries = {
  __typename?: "StoreQueries"
  getAllStores: Array<Store>
  getStoreById: Store
}

export type StoreQueriesGetStoreByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type StoreMutations = {
  __typename?: "StoreMutations"
  createStore?: Maybe<Scalars["Boolean"]>
  updateStore?: Maybe<Scalars["Boolean"]>
  deleteStore?: Maybe<Scalars["Boolean"]>
}

export type StoreMutationsCreateStoreArgs = {
  transactionStoreId?: Maybe<Scalars["String"]>
  skuId?: Maybe<Scalars["String"]>
  skuPurchasePrice?: Maybe<Scalars["String"]>
  skuRetailPrice?: Maybe<Scalars["String"]>
}

export type StoreMutationsUpdateStoreArgs = {
  transactionStoreId?: Maybe<Scalars["String"]>
  skuId?: Maybe<Scalars["String"]>
  skuPurchasePrice?: Maybe<Scalars["String"]>
  skuRetailPrice?: Maybe<Scalars["String"]>
}

export type StoreMutationsDeleteStoreArgs = {
  transactionStoreId?: Maybe<Scalars["String"]>
}
