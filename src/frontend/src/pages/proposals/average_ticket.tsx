import {useQuery} from "@apollo/client";
import {Maybe} from "graphql/jsutils/Maybe";
import React, {ChangeEvent, useState} from "react";
import {FormationOfAverageTicketGrowth} from "../../api/suggestion-query";
import {CustomField} from "../../components/customField";
import {Field, FieldType} from "../../components/form";
import {
    Suggestion,
    SuggestionQueriesFormationOfAverageTicketGrowthArgs,
    SuggestionQuery
} from "../../contracts/suggestion";
import {Proposal} from "../proposal";

export function Average_ticket() {
    const columns = [
        {key: 'customerId', header: 'Customer Id'},
        {key: 'requiredCheckMeasure', header: 'Required check measure'},
        {key: 'offerGroup', header: 'Offer group'},
        {key: 'maxDiscountDepth', header: 'Max discount depth'},
    ];

    const fields = [
        {
            key: "workMode",
            label: "Work mode (1 - per period, 2 - per quantity)",
            type: FieldType.Number,
            min: 1,
            max: 2
        },
        {
            key: "startDate",
            label: "Start date",
            type: FieldType.Date
        },
        {
            key: "endDate",
            label: "End date",
            type: FieldType.Date
        },
        {
            key: "numberTransactions",
            label: "Number transactions",
            type: FieldType.Number
        },
        {
            key: "coefficientIncrease",
            label: "Coefficient increase",
            type: FieldType.Number
        },
        {
            key: "maxChurnIndex",
            label: "Max churn index",
            type: FieldType.Number
        },
        {
            key: "maxShareTransactionsDiscount",
            label: "Max share transactions discount",
            type: FieldType.Number,
            min: 0,
            max: 100
        },
        {
            key: "acceptableMarginShare",
            label: "Acceptable Margin Share",
            type: FieldType.Number,
            min: 0,
            max: 100
        },
    ]

    const [currentModel, setCurrentModel] = useState({
        workMode: 1,
        startDate: "2021-01-02",
        endDate: "2021-10-02",
        numberTransactions: 0,
        coefficientIncrease : 1.15,
        maxChurnIndex: 3,
        maxShareTransactionsDiscount: 70,
        acceptableMarginShare: 30
    });

    const handleValueChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>, field: Field) => {
        setCurrentModel((old: any) => {
            let changed: any = {};
            Object.assign(changed, old);
            if (field.type === FieldType.Number) {
                changed[field.key] = +e.target['value'];
            } else {
                changed[field.key] = e.target['value'];
            }
            return changed;
        })
    }

    const {loading, data, error} =
        useQuery<SuggestionQuery, SuggestionQueriesFormationOfAverageTicketGrowthArgs>(FormationOfAverageTicketGrowth,
            {
                variables: {
                    suggestionInput: currentModel
                }
            });

    const rows: Maybe<Suggestion>[] = data?.suggestion?.formationOfAverageTicketGrowth ?
        data.suggestion.formationOfAverageTicketGrowth : [];

    const [importError, setImportError] = useState<String>("");

    const tableKey = 'average_ticket'

    const saveFile = (blob: Blob, filename: string) => {
        const a = document.createElement('a');
        document.body.appendChild(a);
        const url = window.URL.createObjectURL(blob);
        a.href = url;
        a.download = filename;
        a.click();
        setTimeout(() => {
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        }, 0)
    }

    const exportCsv = async () => {
        setImportError("");
        if (!tableKey) {
            return;
        }
        const formData = new FormData();

        formData.append("file_name", tableKey);
        fetch("/exportFromFunction", {method: "POST", body: formData})
          // вариант с файлом на запрос
          .then( res => {
              if (res.ok) {
                  return res.blob()
              } else {
                  setImportError("Export file error");
              }
          })
          .then( blob => {
              if (blob) {
                  saveFile(blob, tableKey + '.scv')
              }
          })
          .catch(_ => {
              setImportError("Export file error");
          })
        ;
    }

  return (
    <div>
      <Proposal></Proposal>
      <h2>Average ticket</h2>
        <div className={"retail-form"}>
            {fields.map(field =>
              <div className={'retail-field'} key={field.key}>{CustomField(field, currentModel, handleValueChange)}</div>)}
        </div>
        <div className={"retail-error"}> { error?.message ? "Error: " + error?.message : ""}</div>
        {loading ? "Loading..." :
            <div className={"retail-table"}>
                <button onClick={() => exportCsv()}>Export csv</button>
                {importError? <span className={"retail-table_error"}>{importError}</span> : null}
                <table>
                    <thead>
                    <tr>
                        {columns.map(column => (
                            <th key={column.key}>{column.header}</th>
                        ))}
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {rows.map((row: any, rowIndex) => (
                        <tr key={rowIndex}>
                            {columns.map(column => (
                                <td key={column.key}>{row[column.key]}</td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>}
    </div>
  )
}
