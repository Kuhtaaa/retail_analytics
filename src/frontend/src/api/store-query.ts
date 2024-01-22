import { gql } from '@apollo/client';

export const All_stores = gql`
  query getAll {
      store {
        getAllStores {
            transactionStoreId,
            skuId,
            skuPurchasePrice,
            skuRetailPrice
        }
    }
  }
`;

export const Store_by_id = gql`
    query getById($transactionStoreId: String!, $skuId: String!) {
        store {
            getStoreById(transactionStoreId: $transactionStoreId, skuId: $skuId) {
                transactionStoreId,
                skuId,
                skuPurchasePrice,
                skuRetailPrice
            }
        }
    }
`;

export const Delete_store = gql`
    mutation delete($transactionStoreId: String!, $skuId: String!) {
        store {
            deleteStore(transactionStoreId: $transactionStoreId, skuId: $skuId)
        }
    }
`;


export const Edit_store = gql`
    mutation update(
        $transactionStoreId: String!,
        $skuId: String!,
        $skuPurchasePrice: String!,
        $skuRetailPrice: String!
    ) {
        store {
            updateStore(
                transactionStoreId: $transactionStoreId,
                skuId: $skuId,
                skuPurchasePrice: $skuPurchasePrice,
                skuRetailPrice: $skuRetailPrice
            )
        }
    }
`;

export const Add_store = gql`
    mutation create(
        $transactionStoreId: String!,
        $skuId: String!,
        $skuPurchasePrice: String!,
        $skuRetailPrice: String!
    ) {
        store {
            createStore(
                transactionStoreId: $transactionStoreId,
                skuId: $skuId,
                skuPurchasePrice: $skuPurchasePrice,
                skuRetailPrice: $skuRetailPrice
            )
        }
    }
`;
