import { gql } from '@apollo/client';

export const All_sku_groups = gql`
  query getAll {
      skuGroup {
        getAllSkuGroups {
            groupId,
            groupName
        }
    }
  }
`;

export const Sku_groups_by_id = gql`
    query getById($id: String!) {
        skuGroup {
            getSkuGroupById(id: $id) {
                groupId,
                groupName
            }
        }
    }
`;

export const Delete_sku_group = gql`
    mutation delete($id: String!) {
        skuGroup {
            deleteSkuGroups(groupId: $id)
        }
    }
`;


export const Edit_sku_group = gql`
    mutation update(
        $groupId: String!,
        $groupName: String!
    ) {
        skuGroup {
            updateSkuGroups(
                groupId: $groupId,
                groupName: $groupName
            )
        }
    }
`;

export const Add_sku_group = gql`
    mutation create(
        $groupName: String!
    ) {
        skuGroup {
            createSkuGroups(
                groupName: $groupName
            )
        }
    }
`;
