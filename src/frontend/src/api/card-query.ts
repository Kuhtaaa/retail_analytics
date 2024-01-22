import { gql } from '@apollo/client';

export const All_cards = gql`
  query getAll {
    card {
        getAllCards {
            customerCardId,
            customerId
        }
    }
  }
`;

export const Cards_by_id = gql`
    query getById($id: String!) {
        card {
            getCardById(id: $id) {
                customerCardId,
                customerId
            }
        }
    }
`;

export const Delete_card = gql`
    mutation delete($id: String!) {
        card {
            deleteCard(customerCardId: $id)
        }
    }
`;


export const Edit_card = gql`
    mutation update(
        $customerCardId: String!,
        $customerId: String!
    ) {
        card {
            updateCard(
                customerCardId: $customerCardId,
                customerId: $customerId
            )
        }
    }
`;

export const Add_card = gql`
    mutation create(
        $customerId: String!
    ) {
        card {
            createCard(
                customerId: $customerId
            )
        }
    }
`;
