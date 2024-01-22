import { Scalars } from "./common";

export type Card = {
  __typename?: "Card"
  customerCardId: Scalars["String"]
  customerId: Scalars["String"]
}

export type Check = {
  __typename?: "Check"
  transactionId: Scalars["String"]
  skuId: Scalars["String"]
  skuAmount: Scalars["String"]
  skuSumm: Scalars["String"]
  skuSummPaid: Scalars["String"]
  skuDiscount: Scalars["String"]
}

export type CommodityMatrix = {
  __typename?: "CommodityMatrix"
  skuId: Scalars["String"]
  skuName: Scalars["String"]
  groupId: Scalars["String"]
}

export type DateOfAnalysisFormation = {
  __typename?: "DateOfAnalysisFormation"
  id: Scalars["String"]
  analysisFormation: Scalars["String"]
}

export type PersonalInformation = {
  __typename?: "PersonalInformation"
  customerId: Scalars["String"]
  customerName: Scalars["String"]
  customerSurname: Scalars["String"]
  customerPrimaryEmail: Scalars["String"]
  customerPrimaryPhone: Scalars["String"]
}

export type SkuGroup = {
  __typename?: "SkuGroup"
  groupId: Scalars["String"]
  groupName: Scalars["String"]
}

export type Store = {
  __typename?: "Store"
  transactionStoreId: Scalars["String"]
  skuId: Scalars["String"]
  skuPurchasePrice: Scalars["String"]
  skuRetailPrice: Scalars["String"]
}

export type Transaction = {
  __typename?: "Transaction"
  transactionId: Scalars["String"]
  customerCardId: Scalars["String"]
  transactionSumm: Scalars["String"]
  transactionDatetime: Scalars["String"]
  transactionStoreId: Scalars["String"]
}

export type User = {
  __typename?: "User"
  id: Scalars["String"]
  email: Scalars["String"]
  name: Scalars["String"]
  username: Scalars["String"]
}
