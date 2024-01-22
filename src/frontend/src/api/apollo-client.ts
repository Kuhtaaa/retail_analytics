import { ApolloClient, ApolloLink, createHttpLink, DefaultOptions, from, InMemoryCache } from '@apollo/client';
import { onError } from "@apollo/client/link/error";
import { authInterceptorCustomFetch } from "./auth-interceptor";

const httpLink: ApolloLink = createHttpLink({
  uri: '/graphql',
  fetch: authInterceptorCustomFetch
});

const defaultOptions: DefaultOptions = {
  watchQuery: {
    fetchPolicy: 'no-cache',
    errorPolicy: 'all',
  },
  query: {
    fetchPolicy: 'no-cache',
    errorPolicy: 'all',
  },
  mutate: {
    fetchPolicy: 'no-cache',
    errorPolicy: 'all',
  },
}

const logoutLink = onError(({networkError, graphQLErrors, operation, forward}) => {
  if (networkError) {
    return;
  }
  return forward(operation).map(response => {
    if (graphQLErrors) {
      response.data = {...response.data, error: graphQLErrors};
    }
    return response;
  });
})

const client = new ApolloClient({
  link: from([logoutLink, httpLink]),
  cache: new InMemoryCache(),
  defaultOptions: defaultOptions,
});

export default client;
