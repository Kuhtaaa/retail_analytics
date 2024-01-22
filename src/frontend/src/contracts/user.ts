import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";
import { User } from "./types";

export type UserQuery = {
  __typename?: "UserQuery"
  user?: Maybe<UserQueries>
}

export type UserQueries = {
  __typename?: "UserQueries"
  getCurrentUserRoles: Array<Scalars["String"]>
  loadUserByUsername: User
  isAdmin: Scalars["Boolean"]
}

export type UserQueriesLoadUserByUsernameArgs = {
  userNameOrEmail?: Maybe<Scalars["String"]>
}
