import { gql } from '@apollo/client';

export const All_date_of_analysis_formations = gql`
  query getAll {
      dateOfAnalysisFormation {
        getAllDateOfAnalysisFormation {
            id,
            analysisFormation
        }
    }
  }
`;

export const Date_of_analysis_formation_by_id = gql`
    query getById($id: String!) {
        dateOfAnalysisFormation {
            getDateOfAnalysisFormationById(id: $id) {
                id,
                analysisFormation
            }
        }
    }
`;

export const Delete_date_of_analysis_formation = gql`
    mutation delete($id: String!) {
        dateOfAnalysisFormation {
            deleteDateOfAnalysisFormation(id: $id)
        }
    }
`;


export const Edit_date_of_analysis_formation = gql`
    mutation update(
        $id: String!,
        $analysisFormation: String!
    ) {
        dateOfAnalysisFormation {
            updateDateOfAnalysisFormation(
                id: $id,
                analysisFormation: $analysisFormation
            )
        }
    }
`;

export const Add_date_of_analysis_formation = gql`
    mutation create(
        $analysisFormation: String!
    ) {
        dateOfAnalysisFormation {
            createDateOfAnalysisFormation(
                analysisFormation: $analysisFormation
            )
        }
    }
`;
