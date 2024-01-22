import { gql } from '@apollo/client';

export const All_checks = gql`
  query getAll {
    check {
        getAllChecks {
            transactionId,
            skuId,
            skuAmount,
            skuSumm,
            skuSummPaid,
            skuDiscount
        }
    }
  }
`;

export const Check_by_id = gql`
    query getById($id: String!) {
        check {
            getCheckById(id: $id) {
                transactionId,
                skuId,
                skuAmount,
                skuSumm,
                skuSummPaid,
                skuDiscount
            }
        }
    }
`;

export const Delete_check = gql`
    mutation delete($id: String!) {
        check {
            deleteCheck(transactionId: $id)
        }
    }
`;


export const Edit_check = gql`
    mutation update(
        $transactionId: String!,
        $skuId: String!,
        $skuAmount: String!,
        $skuSumm: String!,
        $skuSummPaid: String!,
        $skuDiscount: String!
    ) {
        check {
            updateCheck(
                transactionId: $transactionId
                skuId: $skuId
                skuAmount: $skuAmount
                skuSumm: $skuSumm
                skuSummPaid: $skuSummPaid
                skuDiscount: $skuDiscount
            )
        }
    }
`;

export const Add_check = gql`
    mutation create(
        $transactionId: String!,
        $skuId: String!,
        $skuAmount: String!,
        $skuSumm: String!,
        $skuSummPaid: String!,
        $skuDiscount: String!
    ) {
        check {
            createCheck(
                transactionId: $transactionId
                skuId: $skuId
                skuAmount: $skuAmount
                skuSumm: $skuSumm
                skuSummPaid: $skuSummPaid
                skuDiscount: $skuDiscount
            )
        }
    }
`;
