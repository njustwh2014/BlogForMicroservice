<template>
  <div class="me-info-base-box">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="头像">
        <el-upload
          class="upload-demo"
          action="https://localhost:8080/upload"
          :file-list="imgs"
          :http-request="uploadSectionFile"
          list-type="picture-card"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="注册邮箱">
        <el-input v-model="form.account" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.gender" placeholder="请选择性别">
          <el-option value="男">
            <i class="fa fa-mars"></i>&nbsp;男
          </el-option>
          <el-option value="女">
            <i class="fa fa-venus"></i>&nbsp;女
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学校">
        <el-input v-model="form.school"></el-input>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.birthdate" style="width: 100%;"></el-date-picker>
      </el-form-item>
      <el-form-item label="爱好">
        <el-input type="textarea" v-model="form.hobby"></el-input>
      </el-form-item>
      <!-- <el-form-item label="个人简介">
        <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="onSubmit">更新或创建个人资料</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { upload } from "@/api/upload";
import {getUserInfo,updateInfo} from '@/api/login'
import { get } from 'http';
export default {
  data() {
    return {
      imgs: [],
      form: {
        userid:0,
        nickname: "",
        account: "",
        gender:"",
        school:"",
        birthdate:'',
        hobby:''
      }
    };
  },
  created(){
    this.getUserInfo();
  },
  methods: {
    uploadSectionFile(param) {
      let that = this;
      let formdata = new FormData();
      if(param.file.size>10485760*3){
        this.$message("请选择一张小于30MB的图片！")
      }else{
        formdata.append("editormd-image-file", param.file);
      upload(formdata)
        .then(data => {
          if (data.data.success == 1) {
            console.log(data.data.url)
            that.imgs.unshift({url:data.data.url});
            console.log(that.imgs)
          } else {
            that.$message({
              message: data.msg,
              type: "error",
              showClose: true
            });
          }
        })
        .catch(err => {
          that.$message({ message: err, type: "error", showClose: true });
        });
      }  
    },
    onSubmit() {
      let data={
        userid:0,
        nickname: "",
        gender:0,
        school:"",
        birthdate:'',
        hobby:'',
        headportraiturl:''
      };
      data.userid=this.form.userid;
      data.nickname=this.form.nickname;
      if(this.form.gender=='男'){
        data.gender=1;
      }
      data.school=this.form.school; 
      data.birthdate=this.form.birthdate;
      data.hobby=this.form.hobby;
      data.headportraiturl=this.imgs[0].url;  
      updateInfo(data).then(data=>{
        this.$message("更新资料成功");
        let userinfo=data.data.data;
        this.imgs=[];
        this.imgs=[{url:userinfo.headportraiturl}];
        console.log(this.imgs);
        this.form.account=userinfo.account;
        this.form.nickname=userinfo.nickname;
        this.form.birthdate=userinfo.birthdate;
        this.form.hobby=userinfo.hobby;
        this.form.school=userinfo.school;
        this.form.gender=userinfo.gender;
        this.form.userid=userinfo.id;
        if(userinfo.gender==1){
          this.form.gender="男";
        }
        if(userinfo.gender==0){
          this.form.gender="女";
        } 
        this.$store.commit('SET_AVATAR',userinfo.headportraiturl)
      }).catch(error=>{
        if (error !== 'error') {
          that.$message({type: 'error', message: '更新用户失败', showClose: true})
        }
      })                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    },
    getUserInfo(){
      getUserInfo().then(data=>{
        let userinfo=data.data.data;
        console.log(userinfo);
        this.imgs.unshift({url:userinfo.headportraiturl});
        console.log(this.imgs);
        this.form.account=userinfo.account;
        this.form.nickname=userinfo.nickname;
        this.form.birthdate=userinfo.birthdate;
        this.form.hobby=userinfo.hobby;
        this.form.school=userinfo.school;
        this.form.gender=userinfo.gender;
        this.form.userid=userinfo.id;
        if(userinfo.gender==1){
          this.form.gender="男";
        }
        if(userinfo.gender==0){
          this.form.gender="女";
        } 
      }).catch(error=>{
        if (error !== 'error') {
          that.$message({type: 'error', message: '获取用户失败', showClose: true})
        }
      })
    }
    
  }
};
</script>
<style scoped>
.me-info-base-box {
  position: absolute;
  width: 800px;
  background-color: white;
  margin-top: 0px;
  margin-left: -180px;
  left: 40%;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}
</style>
