import { ApolloProvider } from "@apollo/client";
import React from 'react';
import { createBrowserRouter, LoaderFunctionArgs, redirect, RouterProvider } from "react-router-dom";
import client from "./api/apollo-client";
import { authProvider } from "./api/auth";
import './App.css';
import { About } from "./pages/about";
import { Cards } from "./pages/data/cards";
import { Checks } from "./pages/data/checks";
import { Commoditymatrix } from "./pages/data/commoditymatrix";
import { Data } from "./pages/data";
import { Datesofa } from "./pages/data/datesofa";
import { Layout } from "./pages/layout";
import { loginAction, loginLoader, LoginPage } from "./pages/login";
import { Personal } from "./pages/data/personal";
import { Proposal } from "./pages/proposal";
import { SkuGroups } from "./pages/data/sku-groups";
import { Stores } from "./pages/data/store";
import { Transactions } from "./pages/data/transactions";
import { Average_ticket } from "./pages/proposals/average_ticket";
import { Cross_sellings } from "./pages/proposals/cross_sellings";
import { Frequency } from "./pages/proposals/frequency";


const router = createBrowserRouter([
  {
    id: "root",
    path: "/",
    loader() {
      // Our root route always provides the user, if logged in
      return { user: authProvider.username };
    },
    Component: Layout,
    children: [
      {
        index: true,
        Component: About,
      },
      {
        path: "data",
        Component: Data,
      },
      {
        path: "proposal",
        Component: Proposal,
      },
      {
        path: "login",
        action: loginAction,
        loader: loginLoader,
        Component: LoginPage,
      },
      {
        path: "transactions",
        loader: protectedLoader,
        Component: Transactions,
      },
      {
        path: "stores",
        loader: protectedLoader,
        Component: Stores,
      },
      {
        path: "sku",
        loader: protectedLoader,
        Component: SkuGroups,
      },
      {
        path: "cards",
        loader: protectedLoader,
        Component: Cards,
      },
      {
        path: "commoditymatrix",
        loader: protectedLoader,
        Component: Commoditymatrix,
      },
      {
        path: "dates",
        loader: protectedLoader,
        Component: Datesofa,
      },
      {
        path: "personal",
        loader: protectedLoader,
        Component: Personal,
      },
      {
        path: "checks",
        loader: protectedLoader,
        Component: Checks,
      },
      {
        path: "average_ticket",
        loader: protectedAdminLoader,
        Component: Average_ticket,
      },
      {
        path: "frequency",
        loader: protectedAdminLoader,
        Component: Frequency,
      },
      {
        path: "cross_selling",
        loader: protectedAdminLoader,
        Component: Cross_sellings,
      },
    ],
  },
  {
    path: "/logout",
    async action() {
      // We signout in a "resource route" that we can hit from a fetcher.Form
      await authProvider.signout();
      return redirect("/");
    },
  },
]);

async function protectedLoader (args: LoaderFunctionArgs): Promise<Response | null> {
  if (!authProvider.isAuthenticated) {
    return redirect("/login");
  }
  return null;
}

async function protectedAdminLoader (args: LoaderFunctionArgs): Promise<Response | null> {
  if (!authProvider.isAdmin) {
    return redirect("/login");
  }
  return null;
}

function App () {

  return (
    <ApolloProvider client={client}>
      <RouterProvider router={router} fallbackElement={<p>Initial Load...</p>} />
    </ApolloProvider>
  );
}

export default App;
