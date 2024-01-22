import { gql } from '@apollo/client';

export const All_commodity_matrices = gql`
  query getAll {
      commodityMatrix {
        getAllCommodityMatrices {
            skuId,
            skuName,
            groupId
        }
    }
  }
`;

export const Commodity_matrix_by_id = gql`
    query getById($id: String!) {
        commodityMatrix {
            getCommodityMatrixById(id: $id) {
                skuId,
                skuName,
                groupId
            }
        }
    }
`;

export const Delete_commodity_matrix = gql`
    mutation delete($id: String!) {
        commodityMatrix {
            deleteCommodityMatrix(skuId: $id)
        }
    }
`;


export const Edit_commodity_matrix = gql`
    mutation update(
        $skuId: String!,
        $skuName: String!,
        $groupId: String!
    ) {
        commodityMatrix {
            updateCommodityMatrix(
                skuId: $skuId,
                skuName: $skuName,
                groupId: $groupId
            )
        }
    }
`;

export const Add_commodity_matrix = gql`
    mutation create(
        $skuName: String!,
        $groupId: String!
    ) {
        commodityMatrix {
            createCommodityMatrix(
                skuName: $skuName,
                groupId: $groupId
            )
        }
    }
`;
