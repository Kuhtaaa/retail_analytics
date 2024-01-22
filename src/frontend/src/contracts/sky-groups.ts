import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { SkuGroup } from "./types";

export type SkuGroupQuery = {
  __typename?: "SkuGroupQuery"
  skuGroup?: Maybe<SkuGroupQueries>
}

export type SkuGroupMutation = {
  __typename?: "SkuGroupMutation"
  skuGroup?: Maybe<SkuGroupMutations>
}

export type SkuGroupQueries = {
  __typename?: "SkuGroupQueries"
  getAllSkuGroups: Array<SkuGroup>
  getSkuGroupById: SkuGroup
}

export type SkuGroupQueriesGetSkuGroupByIdArgs = {
  id?: Maybe<Scalars["String"]>
}

export type SkuGroupMutations = {
  __typename?: "SkuGroupMutations"
  createSkuGroups?: Maybe<Scalars["Boolean"]>
  updateSkuGroups?: Maybe<Scalars["Boolean"]>
  deleteSkuGroups?: Maybe<Scalars["Boolean"]>
}

export type SkuGroupMutationsCreateSkuGroupsArgs = {
  groupId?: Maybe<Scalars["String"]>
  groupName?: Maybe<Scalars["String"]>
}

export type SkuGroupMutationsUpdateSkuGroupsArgs = {
  groupId?: Maybe<Scalars["String"]>
  groupName?: Maybe<Scalars["String"]>
}

export type SkuGroupMutationsDeleteSkuGroupsArgs = {
  groupId?: Maybe<Scalars["String"]>
}
