import axios from 'axios';

export const apiURL = 'http://gloomhaven-helper.us-west-2.elasticbeanstalk.com/api/'

export const configuredAxios = axios.create({
  baseURL: apiURL,
  timeout: 4000
});