import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import {
  Add_date_of_analysis_formation,
  All_date_of_analysis_formations,
  Delete_date_of_analysis_formation,
  Edit_date_of_analysis_formation
} from "../../api/date-of-analysis-formation-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import { DateOfAnalysisFormation } from "../../contracts";
import { DateOfAnalysisFormationQueries, DateOfAnalysisFormationQuery } from "../../contracts/date-of-analysis";
import { Data } from "../data";


export const Datesofa: React.FC = () => {
  const columns = [
    {key: 'id', header: 'Id'},
    {key: 'analysisFormation', header: 'Analysis Formation'},
  ];

  const {loading, error, data, refetch} =
    useQuery<DateOfAnalysisFormationQuery, DateOfAnalysisFormationQueries>(All_date_of_analysis_formations);

  const [deleteTransaction, deleteResult] = useMutation(Delete_date_of_analysis_formation);

  const rows: Maybe<DateOfAnalysisFormation>[] = data?.dateOfAnalysisFormation?.getAllDateOfAnalysisFormation ?
    data.dateOfAnalysisFormation.getAllDateOfAnalysisFormation : [];

  const onDelete = async (row: DateOfAnalysisFormation) => {
    await deleteTransaction({variables: {id: row?.id}})
    await refetch()
  }

  const editProps = {
    fields: [
      {
        key: "analysisFormation",
        label: "Date of analysis Formation",
        type: FieldType.DateTime
      }
    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Dates of analysis</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_date_of_analysis_formation}
          editTitle={"Edit date"}
          addDocumentNode={Add_date_of_analysis_formation}
          addTitle={"Add date"}
          tableKey={"date_of_analysis_formation"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
