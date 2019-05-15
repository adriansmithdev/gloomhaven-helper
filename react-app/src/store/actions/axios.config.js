import axios from 'axios';

export const apiURL = 'http://localhost:5000/api/'

export const configuredAxios = axios.create({
  baseURL: apiURL,
  timeout: 4000
});