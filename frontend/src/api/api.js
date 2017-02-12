import axios from "axios";

let axiosInstance = axios.create({
    baseURL: window.location.hostname === 'localhost' ?
        'http://localhost:8080' : 'https://coffee-talks-backend.cfapps.io'
});

export function apiGet(url) {
    return axiosInstance.get(url);
}

export function apiPost(url, body){
    return axiosInstance.post(url, body)
}

export function apiPut(url, body){
    return axiosInstance.put(url, body)
}