import Vue from 'vue'
import Router from 'vue-router'
// import home from '@/components/home'
// import login from '@/components/login'
// import regist from '@/components/regist'
import forgetpassword from '@/components/forgetpassword'
import store from '@/store';
import { Message } from 'element-ui';
import {getToken} from '@/request/token'
import { resolve } from 'upath';

Vue.use(Router)

//按需加载
// const Index = resolve => require(['@/views/Index.vue'], resolve)
const home = resolve => require(['@/components/home.vue'], resolve)
const login = resolve => require(['@/components/login.vue'], resolve)
const regist = resolve => require(['@/components/regist.vue'], resolve)
const test = resolve => require(['@/components/userinfonew/index'], resolve)
const index=resolve=>require(['@/components/view/index.vue'],resolve)
const blogview=resolve=>require(['@/components/view/blog/BlogView.vue'],resolve)
const blogwrite=resolve=>require(['@/components/view/blog/BlogWrite.vue'],resolve)
const blogcategorytag=resolve=>require(['@/components/view/blog/BlogCategoryTag.vue'],resolve)
const infoindex=resolve=>require(['@/components/userinfo/infoindex'],resolve)
const baseinfo=resolve=>require(['@/components/userinfo/childComponents/baseinfo'],resolve)
const following=resolve=>require(['@/components/userinfo/childComponents/following'],resolve)
const systemmessage=resolve=>require(['@/components/userinfo/childComponents/systemmessage'],resolve)
const searchresult=resolve=>require(['@/components/searchResult.vue'],resolve)

const router=new Router({
  routes: [
    {
      path: '/write/:id?',
      component: blogwrite,
      meta: {
        requireLogin: true
      },
    },
    {
      path: '',
      // name: 'home',
      component: home,
      children:[
        {path:'/',
        component:index
        },
        {
          path: '/view/:id',
          component: blogview,
          meta: {
            requireLogin: true
          }
        },
        {
          path:'/userinfo/:id',
          component:infoindex,
          children:[
            {
              path:'/userinfo/:id',
              component:baseinfo,
              meta: {
                requireLogin: true
              }
            },
            {
              path:'/systemmessage',
              component:systemmessage,
              meta: {
                requireLogin: true
              }
            },
            // {
            //   path:'/following',
            //   component:following,
            //   meta: {
            //     requireLogin: true
            //   }
            // }
          ]
        },
    
        {
          path:'/test',
          component:test
        },
        {
          path: '/articles/:type/:id',
          component:blogcategorytag
        },
        {
          path:'search/:searchData',
          component:searchresult

        }
      ]

    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/regist',
      name: 'regist',
      component: regist
    },
    {
      path: '/forgetpassword',
      name: 'forgetpassword',
      component: forgetpassword
    },
    {
      path:'/test',
      name:test,
      component:test
    }
  ],
  scrollBehavior (to, from, savedPosition) {
    // ...
    return {x:0,y:0}
  }
})

router.beforeEach((to,from,next)=>{
  if (to.meta.title) {//判断是否有标题
    document.title = to.meta.title
  }
  if(getToken()){
    if(to.path==='/login'){
      next({path:'/'})}else{
        if(store.state.email.length===0){
          store.dispatch('getUserInfo').then(data=>{
            next()
          }).catch(()=>{
            next({path:'/'})
          })
        }else{
          next()
        }
      }
    }else{
      if(to.matched.some(r=>r.meta.requireLogin)){
        Message({
          type:'warning',
          showClose:true,
          message:'请先登录哦'
        })
      }else{
        next();
      }
    }
  }
)

export default router