import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import { Add_sku_group, All_sku_groups, Delete_sku_group, Edit_sku_group } from "../../api/sku-group-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import { SkuGroup, SkuGroupQueries, SkuGroupQuery } from "../../contracts";
import { Data } from "../data";


export const SkuGroups: React.FC = () => {
  const columns = [
    {key: 'groupId', header: 'Group Id'},
    {key: 'groupName', header: 'Group name'},
  ];

  const {loading, error, data, refetch} =
    useQuery<SkuGroupQuery, SkuGroupQueries>(All_sku_groups);

  const [deleteTransaction, deleteResult] = useMutation(Delete_sku_group);

  const rows: Maybe<SkuGroup>[] = data?.skuGroup?.getAllSkuGroups ?
    data.skuGroup.getAllSkuGroups : [];

  const onDelete = async (row: SkuGroup) => {
    await deleteTransaction({variables: {id: row?.groupId}})
    await refetch()
  }

  const editProps = {
    fields: [
      {
        key: "groupName",
        label: "Group name",
        type: FieldType.String
      },
    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Sku groups</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_sku_group}
          editTitle={"Edit sku group"}
          addDocumentNode={Add_sku_group}
          addTitle={"Add sky group"}
          tableKey={"sky_groups"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
