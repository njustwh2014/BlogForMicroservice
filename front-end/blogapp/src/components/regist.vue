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
      <h1>欢迎注册</h1>

      <el-form ref="user" :model="user" :rules="rules" status-icon>
        <el-form-item prop="email">
          <el-input  placeholder="注册邮箱" v-model= "user.email"></el-input>
        </el-form-item>


        <el-form-item prop="password">
          <el-input type="password" placeholder="密码" v-model= "user.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="checkPass">
          <el-input type="password" placeholder="确认密码" v-model= "user.checkPass" autocomplete="off"></el-input>
        </el-form-item>

        <!-- <el-form-item>
          <el-input placeholder="请输入验证码" v-model="capture.code">
          <template slot="append" >
            <p @click="sendCode('user')" class="me-cap">{{msg}}</p>
          </template>
          </el-input>
        </el-form-item> -->

        <el-form-item>
        <el-input placeholder="请输入验证码" type="text" class="text" v-model="capture.code"></el-input><el-button  @click="sendCode('user')" class="btn">{{msg}}</el-button>
        </el-form-item>
        
        
        
        <el-form-item size="small" class="me-login-button">
          <!-- <el-button type="primary" @click.native.prevent="register('user')" icon="el-icon-upload">注册</el-button> -->
          <el-button type="primary" icon="el-icon-upload" @click="register('capture')">注册</el-button>
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
import {checkEmail,sendCaptcha,regist} from '@/api/login'
import { randomBytes } from 'crypto';
const count=120;
let curcount=count;
let InterValObj=null;
let randomCode='';//发送的验证码
let captchaflag=true;
  export default {
    name: 'regist',
    data() {    
      var validateEmail = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请正确填写邮箱'));
        } else {
          if (value !== '') { 
            var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(!reg.test(value)){
              callback(new Error('请输入有效的邮箱'));
            }else{
              // 邮箱重复性检测
              checkEmail(value).then(data=>{
                if(data.data.data===true){
                  callback(new Error('该邮箱已被注册'));
                }
                else{
                  callback();
                }
              }).catch(error=>{
                callback(new Error('请求服务器出错'))
              })
            }
          }
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.user.checkPass !== '') {
            this.$refs.user.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.user.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };


      return{
        msg:'获取验证码',
        user: {
          email:'',
          password:'',
          checkPass:''
        },
        capture:{
          code:''
        },
        rules: {
          email:[{validator:validateEmail,trigger:'blur'}],
          password:[{ validator: validatePass, trigger: 'blur' }],
          checkPass:[{ validator: validatePass2, trigger: 'blur' }] ,  
        }

      }
    },
    methods: {
      sendCode(user){
        // 邮箱正确和密码均正确时时
        if(captchaflag){
          this.$refs[user].validate((ispass)=>{
          if(ispass){

            for(let i=0;i<6;i++){
              randomCode=randomCode+(parseInt(Math.random()*10)).toString();
            }//产生验证码
            this.msg="请在" + curcount + "s内输入验证码";
            InterValObj=window.setInterval(this.SetRemainTime, 1000);//开启定时器，每一秒执行一次

            console.log(randomCode);
            console.log(this.user.email)
            console.log(this.msg)
            sendCaptcha(randomCode,this.user.email).then(data=>{
              captchaflag=false;
            }).catch(error=>{
              this.$message('获取验证码失败，请重试')
            })           
          }
        })
        }else{
          this.$message('稍后再试')
        }
        
      },
      SetRemainTime(){
        if (curcount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            this.msg='重新获取验证码'
            randomCode='';//清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
            captchaflag=true;
            curcount=count;
        }
        else {
            curcount--;
            this.msg="请在" + curcount + "s内输入验证码";
        }
      },
      register(capture){
        if(captchaflag){
          this.$message('请获取验证码')
        }else{
          if(this.capture.code!=randomCode){
            this.$message('验证码错误')
          }else{
            let that=this
          that.$store.dispatch('regist',that.user).then(()=>{
            this.$message("注册成功")
            this.$router.push({path: '/'})

          }).catch((error)=>{
          this.$message('注册失败')
          })  
          }
        }

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
    height: 490px;
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