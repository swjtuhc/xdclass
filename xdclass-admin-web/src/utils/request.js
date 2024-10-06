import axios from 'axios'
import router from '../router'
import { Message } from 'element-ui'
import store from '@/store';

axios.defaults.timeout = 30 * 1000;

let host = "http://localhost:9001"
// host = "https://hlj-api.ximeng.cloud/"
// host = "https://hlj-api.xiaolietou.top/"


axios.defaults.baseURL = host
axios.defaults.withCredentials = true

export const url = host;

axios.interceptors.request.use(config => {
    const tokenName = localStorage.getItem("token-name");
    const tokenValue = localStorage.getItem("token-value");
    if (tokenName && tokenValue) {
        // 判断是否存在token，如果存在的话，则每个http header都加上token
        config.headers[tokenName] = tokenValue;
    }
    return config;
}, err => {
    Message({
        message: '请求超时',
        type: 'error',
    })
    return Promise.resolve(err);
})
axios.interceptors.response.use(data => {
    if (data.code && data.code == 200 && data.data.msg) {
        if (data.data.code != 200) {
            Message({
                message: data.data.msg,
                type: 'error',
            })
        }
    }
    if (data.data.code == 403) {
        localStorage.clear()
        store.commit('logout')
        router.push("/")
    }
    return data;
}, err => {
    Message({
        message: '服务器异常',
        type: 'error',
    })
    return Promise.resolve(err);
})



let base = '';

export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    });
}
export const uploadFileRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            return ret
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}
export const deleteRequest = (url) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`
    });
}
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        params: params,
    });
}

export const getDownloadRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        params: params,
        responseType: 'blob',
        headers: { 'Content-Type': 'multipart/form-data;boundary=' },
    });
}

export const postStreamRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        responseType: 'stream'

    });
}

export default { getRequest, postRequest, deleteRequest, putRequest, uploadFileRequest, getDownloadRequest, postStreamRequest }