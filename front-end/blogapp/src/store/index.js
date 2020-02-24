import Vue from 'vue'
import Vuex from 'vuex'
import {getToken,setToken,removeToken} from '@/request/token'
import {login, getUserInfo, logout, regist,getbackaccount} from '@/api/login'
Vue.use(Vuex);
const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
  state: {
    id: '',
    email: '',
    nickname: '',
    // 后面考虑前端加盐加密
    avatar: '',
    token: getToken(),

  },
  mutations:{
    SET_TOKEN: (state,token)=>{
      state.token=token;
    },

    SET_EMAIL: (state,email)=>{
      state.email=email;
    },
    SET_NICKNAME: (state,nickname)=>{
      state.nickname=nickname;
    },
    SET_AVATAR: (state,avatar)=>{
      state.avatar=avatar;
    },
    SET_ID:(state,id)=>{
      state.id=id;
    }
  },
  actions:{
    login({commit},user){
      return new Promise((resolve,reject)=>{
        login(user.email,user.password).then(data=>{
          commit('SET_TOKEN',data.data.data.token)//改变状态中的token
          commit('SET_AVATAR',data.data.data.headportraiturl)
          commit('SET_NICKNAME',data.data.data.nickname)
          commit('SET_ID',data.data.data.id)
          commit('SET_EMAIL',data.data.data.account)
          setToken(data.data.data.token)//存储token到本地
          resolve()
        }).catch(error=>{
          reject(error)
        })
      })
    },
    getUserInfo({commit},state){
      let that=this
      return new Promise((resolve,reject)=>{
        getUserInfo().then(data=>{
          if(data.data.data){
            commit('SET_AVATAR',data.data.data.headportraiturl)
            commit('SET_NICKNAME',data.data.data.nickname)
            commit('SET_ID',data.data.data.id)
            commit('SET_EMAIL',data.data.data.account)
          }else{
            commit('SET_AVATAR','')
            commit('SET_NICKNAME','')
            commit('SET_ID','')
            commit('SET_EMAIL','')
            removeToken()
          }
          resolve(data)
        }).catch(error=>{
          reject(error)
        })
      })
    },
    regist({commit},user){
      let that=this;
      return new Promise((resolve,reject)=>{
        regist(user.email,user.password).then(data=>{
          commit('SET_TOKEN',data.data.data.token)//改变状态中的token
          commit('SET_AVATAR',data.data.data.headportraiturl)
          commit('SET_NICKNAME',data.data.data.nickname)
          commit('SET_ID',data.data.data.id)
          commit('SET_EMAIL',data.data.data.account)
          setToken(data.data.data.token)//存储token到本地
          resolve()
        }).catch(error=>{
          reject(error)
        })
      })
    },
    getbackaccount({commit},user){
      let that=this;
      return new Promise((resolve,reject)=>{
        getbackaccount(user.email,user.password).then(data=>{
          commit('SET_TOKEN',data.data.data.token)//改变状态中的token
          commit('SET_AVATAR',data.data.data.headportraiturl)
          commit('SET_NICKNAME',data.data.data.nickname)
          commit('SET_ID',data.data.data.id)
          commit('SET_EMAIL',data.data.data.account)
          setToken(data.data.data.token)//存储token到本地
          resolve()
        }).catch(error=>{
          reject(error)
        })
      })
    },
    logout({commit},state){
      return new Promise((resolve,reject)=>{
        logout().then(()=>{
          commit('SET_AVATAR','')
          commit('SET_NICKNAME','')
          commit('SET_ID','')
          commit('SET_EMAIL','')
          removeToken(),
          resolve()
        }).catch(error=>{
          reject(error)
        })
      })
    }
  }
});