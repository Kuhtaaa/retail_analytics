import { Maybe } from "graphql/jsutils/Maybe";
import { Scalars } from "./common";

export type IoFileQuery = {
  __typename?: "IoFileQuery"
  ioFileQueries?: Maybe<IoFileQueries>
}

export type IoFileQueries = {
  __typename?: "IOFileQueries"
  customExport?: Maybe<Scalars["Boolean"]>
  exportFromTable?: Maybe<Scalars["Boolean"]>
  importToTable?: Maybe<Scalars["Boolean"]>
}

export type IoFileQueriesCustomExportArgs = {
  tableName?: Maybe<Scalars["String"]>
}

export type IoFileQueriesExportFromTableArgs = {
  tableName?: Maybe<Scalars["String"]>
}

export type IoFileQueriesImportToTableArgs = {
  tableName?: Maybe<Scalars["String"]>
}
