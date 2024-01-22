import { useMutation } from "@apollo/client";
import { DocumentNode } from "graphql/language";
import moment from "moment";
import React, { ChangeEvent, useState } from "react";
import Modal from "react-modal";
import { CustomField } from "./customField";

export enum FieldType {
  String,
  Number,
  Date,
  DateTime,
  Email,
  Phone,
  Dropdown,
}

export interface Field {
  options?: {value: any, label: string}[];
  key: string;
  label: string;
  type: FieldType;
  pattern?: string;
  min?: string | number | undefined;
  max?: string | number | undefined;
}

// Пропсы для компонента редактирования строки
export interface FormProps<T> {
  onSubmit: (model: T) => void;
  onCancel: () => void;
  fields: Field [];
  model: T;
  title: string;
  documentNode: DocumentNode;
}

export interface IModel {
  [key: string]: string | number | any;
}


export const CustomForm: React.FC<FormProps<IModel>> = (props: FormProps<IModel>) => {
  const {title, model, fields, onSubmit, onCancel, documentNode} = props;

  const [currentModel, setCurrentModel] = useState(model);
  const [isConfirmModalOpen, setConfirmModalOpen] = useState(false);
  const [error, setError] = useState("");
  const [editModel, editResult] = useMutation(documentNode);

  const handleValueChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>, field: Field) => {
    setCurrentModel((old: IModel) => {
      let changed: IModel = {};
      Object.assign(changed, old);
      switch (field.type) {
        default:
          changed[field.key] = e.target['value'];
          break;
      }
      return changed;
    })
  }

  const handleConfirmOpen = async () => {
    if (validateForm()) {
      setConfirmModalOpen(true)
    }
  }

  const handleConfirm = async () => {
    await handleSubmit(currentModel);
  }

  const closeConfirmModal = async () => {
    setConfirmModalOpen(false)
  }

  const validateForm = (): boolean => {
    let isValid = true;
    setError("");
    fields.forEach(field => {
      switch (field.type) {
        case FieldType.Phone:
          if (currentModel[field.key].match("^((\\+7)+([0-9]){10})$") == null) {
            setError(error => error ? error += ", invalid phone number" : "Invalid phone number");
            isValid = false;
          }
          break;
        case FieldType.Email:
          if (currentModel[field.key].match("^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,}[0-9A-Za-z]{1})|([0-9А-Яа-я]{1}[-0-9А-я\\.]{0,}[0-9А-Яа-я]{1}))@([-A-Za-z]{1,}\\.){1,2}[-A-Za-z]{2,})$") == null) {
            setError(error => error ? error += ", invalid email" : "Invalid email");
            isValid = false;
          }
          break;
        case FieldType.String:
          if (field.pattern && currentModel[field.key].match(field.pattern) == null) {
            setError(error => error ? error += `, invalid ${field.label}` : `Invalid ${field.label}`);
            isValid = false;
          }
          break;
        default:
          break;
      }
    })
    return isValid;
  }

  const handleSubmit = async (newModel: IModel) => {
    fields.forEach(field => {
      switch (field.type) {
        case FieldType.DateTime:
          newModel[field.key] = newModel[field.key] ? moment(newModel[field.key]).format("yyyy-M-D hh:mm:ss") : ""
          break;
        default:
          break;
      }
    })
    if (!validateForm()) {
      return;
    }
    editModel({variables: newModel})
      .then(result => {
        closeConfirmModal()
        if (result?.data?.error && result?.data?.error[0].message) {
          setError(result?.data?.error[0].message);
        } else {
          onSubmit(newModel)
        }
      }).catch((error: Error) => {
      closeConfirmModal()
      setError(error.message);
    });
  }

  return (
    <div className={"retail-form"}>
      <h2>{title}</h2>
      {fields.map(field => <div className={'retail-field'}
                                key={field.key}>{CustomField(field, currentModel, handleValueChange)}</div>)}
      {error ? <div className={"retail-form_error"}> {error} </div> : null}
      <button onClick={handleConfirmOpen}>Save</button>
      <button onClick={onCancel}>Cancel</button>
      <Modal isOpen={isConfirmModalOpen} onRequestClose={closeConfirmModal} ariaHideApp={false}>
        <div className={"retail-form retail-form-confirm"}>
          <div>Are you sure you want to change?</div>
          <button onClick={handleConfirm}>Confirm</button>
          <button onClick={closeConfirmModal}>Cancel</button>
        </div>
      </Modal>
    </div>
  );
};
