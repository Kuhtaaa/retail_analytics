import { gql } from '@apollo/client';

export const Get_current_user_roles = gql`
  query getCurrentUserRoles {
    user {
        getCurrentUserRoles,
        isAdmin,        
    }
  }
`;

export const Get_user_by_username = gql`
    query loadUserByUsername($userNameOrEmail: String!) {
        user {
            loadUserByUsername(userNameOrEmail: $userNameOrEmail) {
                id,
                email,
                name,
                username
            }
        }
    }
`;

export const Is_user_admin = gql`
    query isAdmin {
        user {
            isAdmin
        }
    }
`;
