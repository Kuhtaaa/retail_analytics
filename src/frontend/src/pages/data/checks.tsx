import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import { Add_check, All_checks, Delete_check, Edit_check } from "../../api/check-query";
import { All_commodity_matrices } from "../../api/commodity-matrix-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import { Check, CheckQueries, CheckQuery, CommodityMatrixQueries, CommodityQuery } from "../../contracts";
import { Data } from "../data";


export const Checks: React.FC = () => {
  const columns = [
    {key: 'transactionId', header: 'Transaction Id'},
    {key: 'skuId', header: 'Sku Id'},
    {key: 'skuAmount', header: 'Amount'},
    {key: 'skuSumm', header: 'Summ'},
    {key: 'skuSummPaid', header: 'Paid'},
    {key: 'skuDiscount', header: 'Discount'},
  ];

  const {loading, error, data, refetch} =
    useQuery<CheckQuery, CheckQueries>(All_checks);

  const [deleteCheck, deleteResult] = useMutation(Delete_check);

  const rows: Maybe<Check>[] = data?.check?.getAllChecks ?
    data.check.getAllChecks : [];

  const onDelete = async (row: Check) => {
    await deleteCheck({variables: {id: row?.transactionId}})
    await refetch()
  }

  const queryResult = useQuery<CommodityQuery, CommodityMatrixQueries>(All_commodity_matrices);

  const editProps = {
    fields: [
      {
        key: "transactionId",
        label: "Transaction Id",
        type: FieldType.Number
      },
      {
        key: "skuId",
        label: "Sku Id",
        type: FieldType.Dropdown,
        options: queryResult.data?.commodityMatrix?.getAllCommodityMatrices ? queryResult.data?.commodityMatrix?.getAllCommodityMatrices.map(commodity => {
          return {value: commodity.skuId, label: commodity.skuName}
        }): []
      },
      {
        key: "skuAmount",
        label: "Amount",
        type: FieldType.Number,
        min: 0
      },
      {
        key: "skuSumm",
        label: "Summ",
        type: FieldType.Number,
        min: 0
      },
      {
        key: "skuSummPaid",
        label: "Paid",
        type: FieldType.Number,
        min: 0
      },
      {
        key: "skuDiscount",
        label: "Discount",
        type: FieldType.Number,
        min: 0
      },

    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Checks</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_check}
          editTitle={"Edit check"}
          addDocumentNode={Add_check}
          addTitle={"Add check"}
          tableKey={"checks"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
