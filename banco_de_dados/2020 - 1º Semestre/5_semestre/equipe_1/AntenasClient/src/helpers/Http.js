import axios from'axios'
import store from '../store/index'

const isProduction = process.env.NODE_ENV === 'production';
const baseUrl = 'https://8080-c43c83d1-0b5b-4d87-b728-49c8542e8775.ws-us02.gitpod.io';
const http = axios.create({
    baseURL: `${ baseUrl }/dev`,
    headers: {
        'Accept': 'application/json',
        'Content': 'application/json',
    }
})

http.interceptors.request.use(config => { 
    const token = store.state.token;
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config; 
}, Promise.reject);

export default http;