import axios from './axiosInstance';

export const sendMessage = (request) => {
  return axios.post('/chat', request);
};
    