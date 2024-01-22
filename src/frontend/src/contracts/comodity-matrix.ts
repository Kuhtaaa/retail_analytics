import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { CommodityMatrix } from "./types";

export type CommodityQuery = {
  __typename?: "CommodityQuery"
  commodityMatrix?: Maybe<CommodityMatrixQueries>
}

export type CommodityMutation = {
  __typename?: "CommodityMutation"
  commodityMatrix?: Maybe<CommodityMatrixMutations>
}

export type CommodityMatrixQueries = {
  __typename?: "CommodityMatrixQueries"
  getAllCommodityMatrices: Array<CommodityMatrix>
  getCommodityMatrixById: CommodityMatrix
}

export type CommodityMatrixQueriesGetCommodityMatrixByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type CommodityMatrixMutations = {
  __typename?: "CommodityMatrixMutations"
  createCommodityMatrix?: Maybe<Scalars["Boolean"]>
  updateCommodityMatrix?: Maybe<Scalars["Boolean"]>
  deleteCommodityMatrix?: Maybe<Scalars["Boolean"]>
}

export type CommodityMatrixMutationsCreateCommodityMatrixArgs = {
  skuId?: Maybe<Scalars["String"]>
  skuName?: Maybe<Scalars["String"]>
  groupId?: Maybe<Scalars["String"]>
}

export type CommodityMatrixMutationsUpdateCommodityMatrixArgs = {
  skuId?: Maybe<Scalars["String"]>
  skuName?: Maybe<Scalars["String"]>
  groupId?: Maybe<Scalars["String"]>
}

export type CommodityMatrixMutationsDeleteCommodityMatrixArgs = {
  skuId?: Maybe<Scalars["String"]>
}
