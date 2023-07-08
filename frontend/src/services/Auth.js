import { api } from '../client/AxiosConfig';

export async function login(username, password) {
  let config = {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: 'Basic bXljbGllbnRpZDpteWNsaWVudHNlY3JldA==',
    },
  };
  return api.post(
    '/oauth2/token',
    { username, password, grant_type: 'password' },
    config,
  );
}
