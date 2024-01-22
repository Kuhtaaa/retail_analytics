import React, { ChangeEvent } from "react";
import { Field, FieldType, IModel } from "./form";

export const CustomField = (field: Field, currentModel: IModel, handleValueChange: (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>, field: Field) => void) => {
  switch (field.type) {
    case FieldType.String:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="text" value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.Number:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="number" min={field.min} max={field.max} value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.Date:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="date" value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.DateTime:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="datetime-local" value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.Email:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="email" value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.Phone:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <input type="text" placeholder="+70000000000" value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}/>
      </div>;
    case FieldType.Dropdown:
      return <div>
        <label htmlFor={field.key}>{field.label}</label>
        <br/>
        <select value={currentModel[field.key]} onChange={(e) => handleValueChange(e, field)}>
          {field.options ? field.options.map(opt => <option value={opt.value}>{opt.label}</option>) : null}
        </select>
      </div>;
    default:
      return <span>Unhandled form type: {field.type}</span>;
  }
}
