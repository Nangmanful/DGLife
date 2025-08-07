import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://10.250.72.191:8080', // 기존 Compose와 동일한 서버 주소
});

export default instance;
