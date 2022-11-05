import axios from 'axios';
import { Cookies } from 'react-cookie';
import { loginCheckAsync } from '../actions/auth';

const apiInstance = () => {

    const cookies = new Cookies();

    const axiosInstance = axios.create({
        baseURL: 'http://localhost:2000',
        responseType: "json"
    });

    axiosInstance.interceptors.request.use(
        config => {
            const token = cookies.get('token');
            if (token != null) {
                config.headers.Authorization = 'Bearer '+ token;
            }
            return config;
        },
        err => {
            return Promise.reject(err);
        }
    );

    axiosInstance.interceptors.response.use(
        config => {
            return config
        },
        err => {
            if (err.response.status == 401) {
                cookies.remove('token');
            }
            return Promise.reject(err);
        }
    )

    return axiosInstance;
}

export default apiInstance;