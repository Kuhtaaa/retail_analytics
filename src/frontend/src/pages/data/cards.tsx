import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import { Add_card, All_cards, Delete_card, Edit_card } from "../../api/card-query";
import { All_personal_informations } from "../../api/personal-information-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import { Card, CardQueries, CardQuery } from "../../contracts";
import { PersonalInformationQueries, PersonalInformationQuery } from "../../contracts/personal-information";
import { Data } from "../data";


export const Cards: React.FC = () => {
  const columns = [
    {key: 'customerCardId', header: 'Customer card Id'},
    {key: 'customerId', header: 'Customer Id'},
  ];

  const {loading, error, data, refetch} =
    useQuery<CardQuery, CardQueries>(All_cards);

  const [deleteTransaction, deleteResult] = useMutation(Delete_card);

  const rows: Maybe<Card>[] = data?.card?.getAllCards ?
    data.card.getAllCards : [];

  const onDelete = async (row: Card) => {
    await deleteTransaction({variables: {id: row?.customerCardId}})
    await refetch()
  }

  const queryResult = useQuery<PersonalInformationQuery, PersonalInformationQueries>(All_personal_informations);

  const editProps = {
    fields: [
      {
        key: "customerId",
        label: "Customer Id",
        type: FieldType.Dropdown,
        options: queryResult.data?.personalInformation?.getAllPersonalInformation ? queryResult.data?.personalInformation?.getAllPersonalInformation.map(info => {
          return {value: info.customerId, label: info.customerName + " " + info.customerSurname + " Phone: " + info.customerPrimaryPhone}
        }): []
      },
    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Cards</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_card}
          editTitle={"Edit card"}
          addDocumentNode={Add_card}
          addTitle={"Add card"}
          tableKey={"cards"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
