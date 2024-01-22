import { Proposal } from "../proposal";
import {Field, FieldType} from "../../components/form";
import React, {ChangeEvent, useState} from "react";
import {useQuery} from "@apollo/client";
import {
    Suggestion,
    SuggestionQueriesFormationOfOffersFocusedOnCrossSellingArgs,
    SuggestionQuery
} from "../../contracts/suggestion";
import {
    FormationOfOffersFocusedOnCrossSelling
} from "../../api/suggestion-query";
import {Maybe} from "graphql/jsutils/Maybe";
import {CustomField} from "../../components/customField";

export function Cross_sellings() {
    const columns = [
        {key: 'customerId', header: 'Customer Id'},
        {key: 'skuOffers', header: 'Sku offers'},
        {key: 'maxDiscountDepth', header: 'Max discount depth'},
    ];

    const fields = [
        {
            key: "numberGroups",
            label: "Number groups",
            type: FieldType.Number,
            min: 0
        },
        {
            key: "maxChurnIndex",
            label: "Max churn index",
            type: FieldType.Number,
            min: 0
        },
        {
            key: "maxConsumptionStabilityIndex",
            label: "Max share transactions discount",
            type: FieldType.Number,
            min: 0
        },
        {
            key: "maxSkuSharePercentage",
            label: "Max sku share percentage",
            type: FieldType.Number,
            min: 0,
            max: 100
        },
        {
            key: "acceptableMarginShare",
            label: "Acceptable margin share",
            type: FieldType.Number,
            min: 0,
            max: 100
        }
    ]

    const [currentModel, setCurrentModel] = useState( {
            numberGroups: 5,
            maxChurnIndex: 3,
            maxConsumptionStabilityIndex: 0.5,
            maxSkuSharePercentage: 100,
            acceptableMarginShare : 30
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

    const [importError, setImportError] = useState<String>("");

    const tableKey = 'cross_sellings'

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

    const {loading, error, data} =
        useQuery<SuggestionQuery, SuggestionQueriesFormationOfOffersFocusedOnCrossSellingArgs>(
            FormationOfOffersFocusedOnCrossSelling,
            {
                variables: {
                    suggestionInput: currentModel
                }
            });

    const rows: Maybe<Suggestion>[] = data?.suggestion?.formationOfOffersFocusedOnCrossSelling ?
        data.suggestion.formationOfOffersFocusedOnCrossSelling : [];

  return (
    <div>
      <Proposal></Proposal>
      <h2>Cross sellings</h2>
        <div className={"retail-form"}>
            {fields.map(field => <div className={'retail-field'} key={field.key}>{CustomField(field, currentModel, handleValueChange)}</div>)}
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
