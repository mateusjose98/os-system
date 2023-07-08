import axios from 'axios';
export const api = axios.create({
  // Configuration
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: import.meta.env.VITE_API_TIMEOUT_MS,
  headers: {
    Accept: 'application/json',
  },
});
