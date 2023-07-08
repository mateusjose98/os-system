import axios from 'axios';
const api = axios.create({
  // Configuration
  baseURL: process.env.API_BASE_URL,
  timeout: process.env.API_TIMEOUT_MS,
  headers: {
    Accept: 'application/json',
  },
});
