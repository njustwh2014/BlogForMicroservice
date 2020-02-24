import axios from 'axios'
axios.defaults.withCredentials = true; //让ajax携带cookie
import {Message} from 'element-ui'
import store from '@/store'
import {getToken} from '@/request/token'
import Cookies from 'js-cookie'
const service=axios.create({
  baseURL:process.env.BASE_API,
  timeout:10000
})


//配置请求拦截器
// service.interceptors.request.use(config=>{
//   if(store.state.token){
//     config.head['token']=getToken();
//   }
//   return config;
// },error=>{
//   Promise.reject(error);
// }
// )


 




//配置响应拦截器




export default service;