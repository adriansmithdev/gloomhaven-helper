import axios from 'axios';

export const apiURL = (process.env.NODE_ENV === 'development') ?
  'http://localhost:5000/api/' :
  'http://gloomhaven-helper.us-west-2.elasticbeanstalk.com/api/';
  
export const configuredAxios = axios.create({
  baseURL: apiURL,
  timeout: 4000
});