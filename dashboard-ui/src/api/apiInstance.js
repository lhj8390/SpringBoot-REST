import axios from "axios";


const apiInstance = () => {
    return axios.create({
        baseURL: 'http://localhost:2000',
        responseType: "json"
    })
}

export default apiInstance;