export const authInterceptorCustomFetch = async (uri: RequestInfo, intitalOptions: RequestInit): Promise<Response> => {
  return new Promise<Response>((resolve, reject) => {
    fetch(uri, intitalOptions)
      .then(result => {
        resolve(result);
      }).catch(error => {
      reject(error)
    })
  })
};
