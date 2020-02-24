import Cookies from 'js-cookie'

export function getToken(){
  return Cookies.get('token')
}

export function setToken(token) {
  return Cookies.set('token', token, { expires: 7 })
}

export function removeToken() {
  return Cookies.remove('token')
}