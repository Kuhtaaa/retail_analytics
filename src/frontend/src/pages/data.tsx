import React from "react";
import { Link } from "react-router-dom";

export function Data() {
  return (
    <div className={"retail-menu retail-menu_submenu"}>
      <nav>
        <ul>
          <li>
            <Link to="/sku">Sku groups</Link>
          </li>
          <li>
            <Link to="/commoditymatrix">Commodity Matrix</Link>
          </li>
          <li>
            <Link to="/stores">Stores</Link>
          </li>
          <li>
            <Link to="/personal">Personal information</Link>
          </li>
          <li>
            <Link to="/cards">Cards</Link>
          </li>
          <li>
            <Link to="/transactions">Transactions</Link>
          </li>
          <li>
            <Link to="/checks">Checks</Link>
          </li>
          <li>
            <Link to="/dates">Date Of Analysis</Link>
          </li>
          <li>
          </li>
        </ul>
      </nav>
    </div>
  )
}
