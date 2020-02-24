import request from '@/request/'
import {getToken} from '@/request/token'


export function login(email,password){
  const data={
    email,
    password
  }
// request配置将与service实例配置合并
  return request({
    url:'/user/login',
    method:'post',
    data
  })
}




//注意后端API
export function logout(){
 const data={

  }
  return request({
    url:'/user/logout',
    method:'post',
    data
  })
}

export function checkEmail(email){
  const data={
    email
  }

  return request({
    url:'/user/check',
    method:'post',
    data
  })
}


//后端尚未实现API
export function getUserInfo(){
  const data={
    token:getToken()
  }
  return request({
    url:'/user/currentUser',
    method:'post',
    data
  })
}

export function updateInfo(data){
  return request({
    url:'/user/updateInfo',
    method:'post',
    data
  })
}

export function regist(email,password){
  const data={
    email,
    password
  }
  return request({
    url:'/user/registeremail',
    method:'post',
    data
  })
}

export function getbackaccount(email,password){
  const data={
    email,
    password
  }

  return request({
    url:'/user/getbackaccount',
    method:'post',
    data
  })
}

export function sendCaptcha(code,email){
  const data={
    code,
    email
  }

  return request({
    url:'/user/captcha',
    method:'post',
    data
  })
}