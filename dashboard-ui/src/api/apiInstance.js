import axios from 'axios';
import { Cookies } from 'react-cookie';

const apiInstance = () => {

    const axiosInstance = axios.create({
        baseURL: 'http://localhost:2000',
        responseType: "json"
    });

    axiosInstance.interceptors.request.use(
        async config => {
            const token = new Cookies().get('token');

            if (token != null) {
                config.headers.Authorization = 'Bearer '+ token;
            }
            return config;
        },
        err => {
            return Promise.reject(err);
        }
    );

    return axiosInstance;
}

export default apiInstance;