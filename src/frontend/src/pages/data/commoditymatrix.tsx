import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import {
  Add_commodity_matrix,
  All_commodity_matrices,
  Delete_commodity_matrix,
  Edit_commodity_matrix
} from "../../api/commodity-matrix-query";
import { All_sku_groups } from "../../api/sku-group-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import {
  CommodityMatrix,
  CommodityMatrixQueries,
  CommodityQuery,
  SkuGroupQueries,
  SkuGroupQuery
} from "../../contracts";
import { Data } from "../data";


export const Commoditymatrix: React.FC = () => {
  const columns = [
    {key: 'skuId', header: 'Sku Id'},
    {key: 'skuName', header: 'Sku name'},
    {key: 'groupId', header: 'Group Id'},
  ];

  const {loading, error, data, refetch} =
    useQuery<CommodityQuery, CommodityMatrixQueries>(All_commodity_matrices);

  const [deleteTransaction, deleteResult] = useMutation(Delete_commodity_matrix);

  const rows: Maybe<CommodityMatrix>[] = data?.commodityMatrix?.getAllCommodityMatrices ?
    data.commodityMatrix.getAllCommodityMatrices : [];

  const onDelete = async (row: CommodityMatrix) => {
    await deleteTransaction({variables: {id: row?.skuId}})
    await refetch()
  }
  const skuGroupQueryResult = useQuery<SkuGroupQuery, SkuGroupQueries>(All_sku_groups);

  const editProps = {
    fields: [
      {
        key: "skuName",
        label: "Sku name",
        type: FieldType.String
      },
      {
        key: "groupId",
        label: "Group Id",
        type: FieldType.Dropdown,
        options: skuGroupQueryResult.data?.skuGroup?.getAllSkuGroups ? skuGroupQueryResult.data?.skuGroup?.getAllSkuGroups.map(group => {
          return {value: group.groupId, label: group.groupName}
        }): []
      },

    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Commodity matrix</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_commodity_matrix}
          editTitle={"Edit commodity matrix"}
          addDocumentNode={Add_commodity_matrix}
          addTitle={"Add commodity matrix"}
          tableKey={"commodity_matrix"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
