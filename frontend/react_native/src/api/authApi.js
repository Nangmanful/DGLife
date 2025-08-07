import axios from './axiosInstance';

export const login = (request) => {
  return axios.post('/auth/login', request);
};

export const signup = (request) => {
  return axios.post('/auth/signup', request);
};
