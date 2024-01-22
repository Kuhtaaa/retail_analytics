import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import React from "react";
import { All_commodity_matrices } from "../../api/commodity-matrix-query";
import { Add_store, All_stores, Delete_store, Edit_store } from "../../api/store-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import {
  CommodityMatrixQueries,
  CommodityQuery,
  Store,
  StoreQueries,
  StoreQuery
} from "../../contracts";
import { Data } from "../data";


export const Stores: React.FC = () => {
  const columns = [
    {key: 'transactionStoreId', header: 'Transaction store Id'},
    {key: 'skuId', header: 'Sku Id'},
    {key: 'skuPurchasePrice', header: 'Sku purchase price'},
    {key: 'skuRetailPrice', header: 'Sku retail price'},
  ];

  const {loading, error, data, refetch} =
    useQuery<StoreQuery, StoreQueries>(All_stores);

  const [deleteStore, deleteResult] = useMutation(Delete_store);

  const rows: Maybe<Store>[] = data?.store?.getAllStores ?
    data.store.getAllStores : [];

  const onDelete = async (row: Store) => {
    await deleteStore({variables: {transactionStoreId: row?.transactionStoreId, skuId: row?.skuId}})
    await refetch()
  }

  const queryResult = useQuery<CommodityQuery, CommodityMatrixQueries>(All_commodity_matrices);


  const editProps = {
    fields: [
      {
        key: "transactionStoreId",
        label: "Transaction store Id",
        type: FieldType.Number,
        min: 0
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
        key: "skuPurchasePrice",
        label: "Sku purchase price",
        type: FieldType.Number,
        min: 0
      },
      {
        key: "skuRetailPrice",
        label: "Sku retail price",
        type: FieldType.Number,
        min: 0
      },

    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Stores</h2>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_store}
          editTitle={"Edit store"}
          addDocumentNode={Add_store}
          addTitle={"Add store"}
          tableKey={"stores"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
