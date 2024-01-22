import React from "react";
import { Link } from "react-router-dom";

export function Proposal() {
  return (
    <div className={"retail-menu retail-menu_submenu"}>
      <nav>
        <ul>
          <li>
            <Link to="/average_ticket">Average ticket</Link>
          </li>
          <li>
            <Link to="/frequency">Frequency Of Visits</Link>
          </li>
          <li>
            <Link to="/cross_selling">Cross Selling</Link>
          </li>
          <li>
          </li>
        </ul>
      </nav>
    </div>
  )
}
