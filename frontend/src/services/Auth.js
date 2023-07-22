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

export async function findByUsername(username) {
  if (!username) throw Error('Username não pode ser null');

  return api.get('/api/users/username/' + username, {
    headers: { Authorization: `Bearer ${token()}` },
  });
}

export async function isTokenValid() {
  return api.get('/validate-token', {
    headers: { token: `${token()}` },
  });
}

function token() {
  const token = localStorage.getItem('token');
  return token;
}
