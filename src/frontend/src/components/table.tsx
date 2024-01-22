import { MutationResult } from "@apollo/client";
import { DocumentNode } from "graphql/language";
import React, { ChangeEvent, useState } from 'react';
import Modal from 'react-modal';
import { authProvider } from "../api/auth";
import { CustomForm, Field } from "./form";

interface TableColumn {
  key: string;
  header: string;
}

interface TableRow {
  [key: string]: string | number; // Предполагается, что значения могут быть строкой или числом
}

interface TableProps<T> {
  columns: TableColumn[];
  rows: T[];
  reload: () => {};
  onDelete?: (row: T) => void;
  deleteResult?: MutationResult<any>;
  fields: Field [];
  editTitle: string;
  addTitle: string;
  editDocumentNode: DocumentNode;
  addDocumentNode: DocumentNode;
  tableKey?: string;
}


export const Table: React.FC<TableProps<any>> = (props) => {
  const {columns, rows, reload, onDelete, deleteResult,
    fields, editTitle, addTitle, editDocumentNode, addDocumentNode, tableKey} = props;
  const [isModalOpen, setModalOpen] = useState(false);
  const [isAddModalOpen, setAddModalOpen] = useState(false);
  const [isConfirmModalOpen, setConfirmModalOpen] = useState(false);
  const [editedRow, setEditedRow] = useState<TableRow | null>(null);
  const [addModel, setAddModel] = useState<TableRow | null>(null);
  const [deleteModel, setDeleteModel] = useState<TableRow | null>(null);
  const [selectedFile, setSelectedFile] = useState<any>(null);
  const [importError, setImportError] = useState<String>("");

  const openEditModal = (row: TableRow) => {
    setEditedRow(row);
    setModalOpen(true);
  };

  const closeEditModal = () => {
    setEditedRow(null);
    setModalOpen(false);
  };

  const openAddModal = () => {
    setAddModel(addModel);
    setAddModalOpen(true);
  };

  const closeAddModal = () => {
    setEditedRow(null);
    setAddModalOpen(false);
  };
  const closeConfirmModal = () => {
    setDeleteModel(null);
    setConfirmModalOpen(false);
  };

  const handleDelete = (row: TableRow) => {
    setDeleteModel(row);
    setConfirmModalOpen(true);
  }

  const handleConfirm = () => {
    if (onDelete) {
      onDelete(deleteModel)
      closeConfirmModal()
    }
  }

  const saveEditedRow = (_: TableRow) => {
    closeEditModal();
    reload();
  };

  const saveAdd = (_: TableRow) => {
    closeAddModal();
    reload();
  };

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

    formData.append("table_name", tableKey);
    fetch("/exportFromTable", {method: "POST", body: formData})
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

  const onFileChange = (event: ChangeEvent<HTMLInputElement>) => {
    setImportError("");
    if (event.target.files) {
      setSelectedFile(event.target.files[0])
    }
  };

  const onFileUpload = async  () => {
    setImportError("");
    if (!selectedFile || !tableKey) {
      return;
    }

    const formData = new FormData();

    formData.append(    "file", selectedFile);
    formData.append("table_name", tableKey);

    let result = await fetch("/import", {method: "POST", body: formData});
    if (result.ok) {
      setSelectedFile(null);
    } else {
      setImportError("Import file error");
    }
  };

  let isAdmin = authProvider.isAdmin;
  return (
    <div className={"retail-table"}>
      {isAdmin ? <button onClick={() => openAddModal()}>Add</button> : null}
      <div className={"retail-table_error"}> {deleteResult?.data?.error ? "Deletion error: " + deleteResult?.data?.error[0].message : ""}</div>
      {isAdmin ? <div>
        <button onClick={() => exportCsv()}>Export csv</button>
        <button onClick={onFileUpload} disabled={!selectedFile}>
          Import csv
        </button>
        <input
          type="file"
          accept=".csv"
          onChange={onFileChange}
        />
        {importError? <span className={"retail-table_error"}>{importError}</span> : null}
      </div> : null}
      <table>
        <thead>
        <tr>
          {columns.map(column => (
            <th key={column.key}>{column.header}</th>
          ))}
          {isAdmin ? <th></th> : null}
          <th></th>
        </tr>
        </thead>
        <tbody>
        {rows.slice(0, 1000).map((row, rowIndex) => (
          <tr key={rowIndex}>
            {columns.map(column => (
              <td key={column.key}>{row[column.key]}</td>
            ))}
            {isAdmin ? <td>
              <button onClick={() => openEditModal(row)}>Edit</button>
              <button onClick={() => handleDelete(row)}>Delete</button>
            </td> : null}
          </tr>
        ))}
        </tbody>
      </table>
      <Modal isOpen={isAddModalOpen} onRequestClose={closeAddModal} ariaHideApp={false}>
        <CustomForm
          onSubmit={saveAdd}
          onCancel={closeAddModal}
          fields={fields}
          model={addModel || {}}
          title={addTitle}
          documentNode={addDocumentNode}
        >
        </CustomForm>
      </Modal>
      <Modal isOpen={isModalOpen} onRequestClose={closeEditModal} ariaHideApp={false}>
        <CustomForm
          onSubmit={saveEditedRow}
          onCancel={closeEditModal}
          fields={fields}
          model={editedRow || {}}
          title={editTitle}
          documentNode={editDocumentNode}
        >
        </CustomForm>
      </Modal>
      <Modal isOpen={isConfirmModalOpen} onRequestClose={closeConfirmModal} ariaHideApp={false}>
        <div className={"retail-form retail-form-confirm"}>
          <div>Are you sure you want to delete?</div>
          <button onClick={handleConfirm}>Confirm</button>
          <button onClick={closeConfirmModal}>Cancel</button>
        </div>
      </Modal>
    </div>
  );
};

