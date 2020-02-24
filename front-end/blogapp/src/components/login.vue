<template>
  <div id="newregist" >
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
          <source src="../../static/vedio/sea.mp4" type="video/mp4">
      </video>-->

    <div class="me-login-box me-login-box-radius">
      <p class="me-logo">
          <router-link to="/">
            <img  src="../assets/logo.png"/>
          </router-link>
      </p>
      <h1>欢迎回家</h1>

      <el-form ref="user" :model="user" :rules="rules" status-icon>
        <el-form-item prop="email">
          <el-input  placeholder="注册邮箱" v-model= "user.email"></el-input>
        </el-form-item>


        <el-form-item >
          <el-input type="password" placeholder="密码" v-model= "user.password" autocomplete="off"></el-input>
        </el-form-item>



        <!-- <el-form-item>
          <el-input placeholder="请输入验证码" v-model="capture.code">
          <template slot="append" >
            <p @click="sendCode('user')" class="me-cap">{{msg}}</p>
          </template>
          </el-input>
        </el-form-item> -->

        
        
        
        <el-form-item size="small" class="me-login-button">
          <!-- <el-button type="primary" @click.native.prevent="register('user')" icon="el-icon-upload">注册</el-button> -->
          <el-button type="primary" icon="el-icon-upload" @click="login('user')">登陆</el-button>
        </el-form-item>

        <el-form-item >
          <router-link class="el-icon-info"  style="float:left" to="/forgetpassword"> 忘记密码？</router-link>
        </el-form-item>
      </el-form>
      
      
      <div class="me-login-design">
        <p>Designed by
          <strong>
            <router-link to="/" class="me-login-design-color">WH</router-link>
          </strong>
        </p>
      </div>

    </div>
  </div>
</template>

<script>
import {getToken} from '@/request/token'
import {mapActions} from 'vuex';
import {getCookie,setCookie} from '@/util/cookie';

  export default {
    name: 'login',
    data() {    
      var validateEmail = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入邮箱'));
        } else {
          var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if(!reg.test(value)){
            callback(new Error('请输入有效的邮箱'));
          }else{
            callback();
          }
          
        }
      };

      return{
        user: {
          email:'',
          password:''
        },
        rules: {
          email:[{validator:validateEmail,trigger:'blur'}],
        }

      }
    },
    methods: {
      login(user){
        this.$refs[user].validate(ispass=>{
          if(ispass){
            let that=this
            console.log(this.user)
            that.$store.dispatch('login',that.user).then(()=>{
              this.$message("登陆成功")
              this.$router.push({path: '/'})

            }).catch((error)=>{
              if(error !== 'error'){
                this.$message("登陆失败")
              }
            })  
          }
        })

      }
    },
    mounted() {
    },
  }
</script>

<style scoped>
  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 350px;
    height: 400px;
    background-color: white;
    margin-top: 60px;
    margin-left: -180px;
    left: 50%;
    padding: 30px;
  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

  .me-login-design {
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    font-size: 18px;
  }

  .me-login-design-color {
    color: #5FB878 !important;
  }
  .me-login-design-plus{
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    font-size: 45px;
    font-style:oblique;
  }
  .me-login-button {
    text-align: center;
  }

  .me-login-button button {
    width: 100%;
  }
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .text{
      width: 45%;
  }
  .btn{
      height: 40px;
      width: 55%;
      background-color: #f5f7fa;
  }
  .me-logo img {
    max-height: 8rem;
    max-width: 100%;
    margin-left: 31%;
  }


</style>