import { gql } from '@apollo/client';

export const All_transactions = gql`
  query getAll {
    transaction {
      getAllTransactions {
        transactionId,
        customerCardId,
        transactionSumm,
        transactionDatetime,
        transactionStoreId
      }
    }
  }
`;


export const All_transactions_filtered = gql`
    query getAllFiltered($dateTimeBegin: String!, $dateTimeEng: String!) {
        transaction {
            getTransactionEntitiesBetweenDate(
                dateTimeBegin: $dateTimeBegin,
                dateTimeEng : $dateTimeEng
            ) {
                transactionId,
                customerCardId,
                transactionSumm,
                transactionDatetime,
                transactionStoreId
            }
        }
    }
`;

export const Delete_transaction = gql`
    mutation delete($id: String!) {
        transaction {
            deleteTransaction(transactionId: $id)
        }
    }
`;


export const Edit_transaction = gql`
    mutation updateTransaction(
        $transactionId: String!,
        $customerCardId: String!,
        $transactionSumm: String!,
        $transactionDatetime: String!,
        $transactionStoreId: String!
    ) {
        transaction {
            updateTransaction(
                transactionId: $transactionId,
                customerCardId: $customerCardId,
                transactionSumm: $transactionSumm,
                transactionDatetime: $transactionDatetime,
                transactionStoreId: $transactionStoreId
            )
        }
    }
`;

export const Add_transaction = gql`
    mutation createTransaction(
        $customerCardId: String!,
        $transactionSumm: String!,
        $transactionDatetime: String!,
        $transactionStoreId: String!
    ) {
        transaction {
            createTransaction(
                customerCardId: $customerCardId,
                transactionSumm: $transactionSumm,
                transactionDatetime: $transactionDatetime,
                transactionStoreId: $transactionStoreId
            )
        }
    }
`;
