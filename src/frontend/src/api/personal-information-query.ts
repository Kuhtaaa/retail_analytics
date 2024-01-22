import { gql } from '@apollo/client';

export const All_personal_informations = gql`
  query getAll {
      personalInformation {
        getAllPersonalInformation {
            customerId,
            customerName,
            customerSurname,
            customerPrimaryEmail,
            customerPrimaryPhone
        }
    }
  }
`;

export const Personal_information_by_id = gql`
    query getById($id: String!) {
        personalInformation {
            getPersonalInformationById(id: $id) {
                customerId,
                customerName,
                customerSurname,
                customerPrimaryEmail,
                customerPrimaryPhone
            }
        }
    }
`;

export const Delete_personal_information = gql`
    mutation delete($id: String!) {
        personalInformation {
            deletePersonalInformation(customerId: $id)
        }
    }
`;


export const Edit_personal_information = gql`
    mutation update(
        $customerId : String!,
        $customerName : String!,
        $customerSurname : String!,
        $customerPrimaryEmail : String!,
        $customerPrimaryPhone : String!
    ) {
        personalInformation {
            updatePersonalInformation(
                customerId : $customerId
                customerName : $customerName
                customerSurname : $customerSurname
                customerPrimaryEmail : $customerPrimaryEmail
                customerPrimaryPhone : $customerPrimaryPhone
            )
        }
    }
`;

export const Add_personal_information = gql`
    mutation create(
        $customerName : String!,
        $customerSurname : String!,
        $customerPrimaryEmail : String!,
        $customerPrimaryPhone : String!
    ) {
        personalInformation {
            createPersonalInformation(
                customerName : $customerName
                customerSurname : $customerSurname
                customerPrimaryEmail : $customerPrimaryEmail
                customerPrimaryPhone : $customerPrimaryPhone
            )
        }
    }
`;
