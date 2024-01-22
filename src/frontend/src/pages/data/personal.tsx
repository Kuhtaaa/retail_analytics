import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import {
  Add_personal_information,
  All_personal_informations,
  Delete_personal_information,
  Edit_personal_information
} from "../../api/personal-information-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import { PersonalInformation } from "../../contracts";
import { PersonalInformationQueries, PersonalInformationQuery } from "../../contracts/personal-information";
import { Data } from "../data";


export const Personal: React.FC = () => {
  const columns = [
    {key: 'customerId', header: 'Id'},
    {key: 'customerName', header: 'Name'},
    {key: 'customerSurname', header: 'Surname'},
    {key: 'customerPrimaryEmail', header: 'Email'},
    {key: 'customerPrimaryPhone', header: 'Phone'},
  ];

  const {loading, error, data, refetch} =
    useQuery<PersonalInformationQuery, PersonalInformationQueries>(All_personal_informations);

  const [deleteTransaction, deleteResult] = useMutation(Delete_personal_information);

  const rows: Maybe<PersonalInformation>[] = data?.personalInformation?.getAllPersonalInformation ?
    data.personalInformation.getAllPersonalInformation : [];

  const onDelete = async (row: PersonalInformation) => {
    await deleteTransaction({variables: {id: row?.customerId}})
    await refetch()
  }

  const editProps = {
    fields: [
      {
        key: "customerName",
        label: "Name",
        type: FieldType.String,
        pattern: "^([А-Я]{1}[а-яё\\- ]{0,}|[A-Z]{1}[a-z\\- ]{0,})$"
      },
      {
        key: "customerSurname",
        label: "Surname",
        type: FieldType.String,
        pattern: "^([А-Я]{1}[а-яё\\- ]{0,}|[A-Z]{1}[a-z\\- ]{0,})$"
      },
      {
        key: "customerPrimaryEmail",
        label: "Email",
        type: FieldType.Email
      },
      {
        key: "customerPrimaryPhone",
        label: "Phone",
        type: FieldType.Phone
      },
    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Personals</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_personal_information}
          editTitle={"Edit personal"}
          addDocumentNode={Add_personal_information}
          addTitle={"Add personal"}
          tableKey={"personal_information"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
