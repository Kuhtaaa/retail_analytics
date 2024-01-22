import { useQuery } from "@apollo/client";
import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { authProvider } from "../api/auth";
import { Get_current_user_roles } from "../api/user-query";
import { UserQueries, UserQuery } from "../contracts";

export function Layout() {

  const {loading, error, data} =
    useQuery<UserQuery, UserQueries>(Get_current_user_roles);
  if (data) {
    authProvider.signin("test");
    authProvider.isAdmin = !!data.user?.isAdmin;
  }
  if (error) {
    authProvider.signout();
  }

  return (<div>
      {loading ? <div> Loading... </div> :
        <div className={"retail-layout"}>
          <div>
            <header className={"retail-header"}>
              <h1><Link to="/">Retail web</Link></h1>
            </header>
            <div className={"retail-menu"}>
              <nav>
                <ul>
                  <li>
                    <Link to="/">About</Link>
                  </li>
                  <li>
                    <Link to="/data">Data</Link>
                  </li>
                  <li>
                    <Link to="/proposal">Proposal</Link>
                  </li>
                  <AuthStatus/>
                </ul>
              </nav>
            </div>
          </div>

          <Outlet/>

        </div>
      }
    </div>
  );
}


export function AuthStatus() {
  const navigate = useNavigate();
  const handleSignout = async () => {
    await authProvider.signout();
    navigate("/login");
  }


  let isLoggingOut = !authProvider.isAuthenticated;

  return (
    <li className={"retail-sign-out"}>
      {authProvider.isAuthenticated ?
        <div>
          <button type="button" disabled={isLoggingOut} onClick={handleSignout}>Sign out</button>
        </div>
        :
        <ul>
          <Link to="/login">Login</Link>
        </ul>
      }

    </li>
  );
}
