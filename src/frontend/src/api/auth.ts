/**
 * This represents some generic auth provider API, like Firebase.
 */
export const authProvider: IAuthProvider = {
  isAuthenticated: false,
  isAdmin: false,
  username: null,

  async signin(username: string) {
    authProvider.isAuthenticated = true;
    authProvider.username = username;
  },

  async signout() {
    try {
      await fetch("/logout", {method: "POST", mode: "cors"});
    } catch (e) {
      //
    }
    authProvider.isAuthenticated = false;
    authProvider.isAdmin = false;
    authProvider.username = "";
  },
};

interface IAuthProvider {
  isAuthenticated: boolean;
  isAdmin: boolean;
  username: null | string;
  signin(username: string): Promise<void>;
  signout(): Promise<void>;
}
