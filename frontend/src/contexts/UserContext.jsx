import React, { createContext, useEffect, useState } from 'react';
import { login, findByUsername, isTokenValid } from '../services/Auth';
import useLocalStorage from '../hooks/useLocalStorage';
import { useNavigate } from 'react-router-dom';
export const UserContext = createContext();

export const UserStorage = ({ children }) => {
  const [data, setData] = useState(null);
  const [logged, setLogin] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [token, setToken] = useLocalStorage('token', '');
  const navigate = useNavigate();
  async function getUser() {
    if (!token) {
      return null;
    }
    const info = JSON.parse(atob(token.split('.')[1]));
    const res = await findByUsername(info.username);

    if (res.status === 200) {
      setData(res.data);
    }
  }
  async function userLogin(username, password) {
    try {
      const res = await login(username, password);
      if (res.status === 200) {
        const { data } = res;
        setToken(data.access_token);
        setLogin(true);
        await getUser();
        navigate('/');
      }
    } catch (error) {
      const msg = error.response.data.error;
      console.error(msg);
      setError(msg);
    }
  }

  useEffect(() => {
    async function autoLogin() {
      if (token) {
        const tokenValid = await isTokenValid();
        console.log(tokenValid);
      }
    }
    autoLogin();
  }, []);

  return (
    <UserContext.Provider value={{ userLogin, data }}>
      {children}
    </UserContext.Provider>
  );
};
