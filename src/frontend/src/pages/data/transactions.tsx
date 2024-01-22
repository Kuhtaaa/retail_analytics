import { useMutation, useQuery } from "@apollo/client";
import { Maybe } from "graphql/jsutils/Maybe";
import moment from "moment";
import React, { ChangeEvent, useState } from "react";
import { All_cards } from "../../api/card-query";
import { All_stores } from "../../api/store-query";
import {
  Add_transaction,
  All_transactions_filtered,
  Delete_transaction,
  Edit_transaction
} from "../../api/transaction-query";
import { FieldType } from "../../components/form";
import { Table } from "../../components/table";
import {
  CardQueries,
  CardQuery,
  StoreQueries,
  StoreQuery,
  Transaction,
  TransactionQueriesGetTransactionEntitiesBetweenDateArgs,
  TransactionQuery
} from "../../contracts";
import { Data } from "../data";


export const Transactions: React.FC = () => {
  const columns = [
    {key: 'transactionId', header: 'Transaction Id'},
    {key: 'customerCardId', header: 'Customer Card Id'},
    {key: 'transactionSumm', header: 'Transaction Summ'},
    {key: 'transactionDatetime', header: 'Transaction Datetime'},
    {key: 'transactionStoreId', header: 'Transaction Store'},
  ];

  const [dateTimeBegin, setDateTimeBegin] = useState(moment("2019-12-08").format().split('+')[0]);
  const [dateTimeEnd, setDateTimeEnd] = useState(moment("2019-12-20").format().split('+')[0]);

  const {loading, error, data, refetch} =
    useQuery<TransactionQuery, TransactionQueriesGetTransactionEntitiesBetweenDateArgs>(All_transactions_filtered,
      {
        variables: {
          dateTimeBegin: moment(dateTimeBegin).format("yyyy-M-D hh:mm:ss"),
          dateTimeEng: moment(dateTimeEnd).format("yyyy-M-D hh:mm:ss")
        }
      });

  const [deleteTransaction, deleteResult] = useMutation(Delete_transaction);

  const rows: Maybe<Transaction>[] = data?.transaction?.getTransactionEntitiesBetweenDate ?
    data.transaction.getTransactionEntitiesBetweenDate : [];

  const onDelete = async (row: Transaction) => {
    await deleteTransaction({variables: {id: row?.transactionId}})
    await refetch()
  }

  function handleDateTimeBegin(ev: ChangeEvent<HTMLInputElement>) {
    if (!ev.target['validity'].valid) return;
    const dt = ev.target['value'];
    setDateTimeBegin(dt);
  }

  function handleDateTimeEnd(ev: ChangeEvent<HTMLInputElement>) {
    if (!ev.target['validity'].valid) return;
    const dt = ev.target['value'];
    setDateTimeEnd(dt);
  }

  const queryResultCards = useQuery<CardQuery, CardQueries>(All_cards);
  const queryResultStores = useQuery<StoreQuery, StoreQueries>(All_stores);

  const editProps = {
    fields: [
      {
        key: "customerCardId",
        label: "Customer Card Id",
        type: FieldType.Dropdown,
        options: queryResultCards.data?.card?.getAllCards ? queryResultCards.data?.card?.getAllCards.map(card => {
          return { value: card.customerCardId, label: "CustomerCardId: " + card.customerCardId + " CustomerId: " + card.customerId }
        }): []
      },
      {
        key: "transactionSumm",
        label: "Summ",
        type: FieldType.Number,
        min: 0
      },
      {
        key: "transactionDatetime",
        label: "Date Time",
        type: FieldType.DateTime
      },
      {
        key: "transactionStoreId",
        label: "Store Id",
        type: FieldType.Dropdown,
        options: queryResultStores.data?.store?.getAllStores ? queryResultStores.data?.store?.getAllStores.map(store => {
          return {value: store.transactionStoreId, label: "TransactionStoreId: " + store.transactionStoreId  + " SkuId: " + store.skuId}
        }): []
      },

    ]
  }

  return (
    <div>
      <Data></Data>
      <h2>Transactions</h2>
      <div className={"retail-table-filter"}>
        <div>
          <label htmlFor="dateTimeBegin">Date time begin</label>
          <br/>
          <input type={"datetime-local"} id={"dateTimeBegin"} value={dateTimeBegin}
                 onChange={handleDateTimeBegin}></input>
        </div>
        <div>
          <label htmlFor="dateTimeEnd">Date time end</label>
          <br/>
          <input type={"datetime-local"} id={"dateTimeEnd"} value={dateTimeEnd}
                 onChange={handleDateTimeEnd}></input>
        </div>
      </div>
      {loading ? "Loading..." :
        <Table
          columns={columns}
          rows={rows}
          reload={refetch}
          onDelete={onDelete}
          deleteResult={deleteResult}
          fields={editProps.fields}
          editDocumentNode={Edit_transaction}
          editTitle={"Edit transaction"}
          addDocumentNode={Add_transaction}
          addTitle={"Add transaction"}
          tableKey={"transactions"}
        />
      }
      {error ? "Loading error " : null}
    </div>
  )
};
