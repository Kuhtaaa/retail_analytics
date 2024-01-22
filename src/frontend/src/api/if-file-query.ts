import { gql } from '@apollo/client';

export const ExportFromTable = gql`
  query exportFromTable($tableName: String!) {
      ioFileQueries {
          exportFromTable(tableName: $tableName)
    }
  }
`;

export const ImportToTable = gql`
    query importToTable($tableName: String!) {
        ioFileQueries {
            importToTable(tableName: $tableName)
        }
    }
`;

export const CustomExport = gql`
    query customExport($tableName: String!) {
        ioFileQueries {
            customExport(tableName: $tableName)
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
