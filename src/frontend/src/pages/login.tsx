import { Form, LoaderFunctionArgs, redirect, useActionData, useLocation } from "react-router-dom";
import client from "../api/apollo-client";
import { authProvider } from "../api/auth";
import { Get_current_user_roles } from "../api/user-query";

export async function loginAction({ request }: LoaderFunctionArgs) {
  try {
    await fetch(request, {mode:'cors'});
    const { error, data} = await client.query({
      query: Get_current_user_roles ,
      variables: {}
    })
    if (data) {
      await authProvider.signin("test");
      authProvider.isAdmin = !!data.user?.isAdmin;
    }
    if (error) {
      await authProvider.signout();
    }
    return redirect("/");
  } catch (error) {
    return {
      error: "Invalid login attempt",
    };
  }

}

export async function loginLoader() {
  if (authProvider.isAuthenticated) {
    return redirect("/");
  }
  return null;
}


export function LoginPage() {
  let location = useLocation();
  let params = new URLSearchParams(location.search);
  let from = params.get("from") || "/";

  // let navigation = useNavigation();
  let isLoggingIn = authProvider.isAuthenticated;

  let actionData = useActionData() as { error: string } | undefined;

  // ToDo add validation

  return (
    <div className={"retail-form"}>
      <p>You must log in to view the page at {from}</p>

      <Form method="post" action="/login" replace>
        <input type="hidden" name="redirectTo" value={from} />
        <label>
          Username: <input name="username" />
        </label>{" "}
        <label>
          Password: <input type={"password"} name="password" />
        </label>{" "}
        <button type="submit" disabled={isLoggingIn}>
          {isLoggingIn ? "Logging in..." : "Login"}
        </button>
        {actionData && actionData.error ? (
          <p style={{ color: "red" }}>{actionData.error}</p>
        ) : null}
      </Form>
    </div>
  );
}
