<template>
  <div class="system-message-base-box">
    <span class="emphasize">未读消息(共{{unreadMessageCount}}条):</span>
    <el-divider></el-divider>
    <div v-for="c in unreadMessageList" :key="c.id" class="text">
      <span @click="handleJumpToPage(c)"><b>{{c.messagedate|format}}:</b>&nbsp;{{c.messagecontent}}</span>
      <el-divider></el-divider>
    </div>
    <span class="emphasize">历史消息:</span>
    <el-divider></el-divider>
    <div v-for="d in readMessageList" :key="d.id" class="text">
      <span @click="handleJumpToPage(d)"><b>{{d.messagedate|format}}:</b>&nbsp;{{d.messagecontent}}</span>
      <el-divider></el-divider>
    </div>
    <el-button type="info" plain @click="handleGetMoreReadMessage()">
      <span v-if="haveReadMessageFlag">查看更多历史消息&nbsp;<i class="el-icon-more-outline"></i></span>
      <span v-else>已加载所有历史消息</span>
    </el-button>
  </div>
</template>

<script>
import {getAllUnreadMessage,getAllReadMessage,changeMessageStatus} from "@/api/message" 
export default {
  name: "systemmessage",
  created(){
    this.handleGetAllUnreadMessage();
    this.handleGetReadMessage();
  },
  data() {
    return {
      unreadMessageCount: 0,
      unreadMessageList:[],
      readMessageList:[],
      haveReadMessageFlag:true,
      batchSize:5,
      index:0,
    };
  },
  methods:{

    /* 
    获取所有未读消息
    */
    handleGetAllUnreadMessage(){
      let userid = this.$store.state.id;
      if(userid!=""){
        getAllUnreadMessage(userid).then(response=>{
          if(response.data.status==200){
            //获取成功
            this.unreadMessageCount=response.data.data.total;
            this.unreadMessageList=response.data.data.systemmessagelist;
          }else{
            //获取失败
            this.$message.error(response.data.msg);
          }
        }).catch(error => {
          if (error !== "error") {
            this.$message({
              type: "error",
              message: "获取未读消息失败！",
              showClose: true
            });
          }
        });
      }
    },
    /*
      根据batch获取历史消息
      */
     handleGetReadMessage(){
      let userid = this.$store.state.id;
      if(userid!=""){
        if(this.haveReadMessageFlag){
          //还有历史消息
          getAllReadMessage(userid,this.index,this.batchSize).then(response=>{
            this.readMessageList=this.readMessageList.concat(response.data.data)
            if(response.data.data.length==this.batchSize){
              this.index=this.index+1;
            }else{
              this.haveReadMessageFlag=false;
            }
          }).catch(error => {
          if (error !== "error") {
            this.$message({
              type: "error",
              message: "获取历史消息失败！",
              showClose: true
            });
          }
        });
        }
      }

     },

     handleGetMoreReadMessage(){
       if(this.haveReadMessageFlag){
         this.handleGetReadMessage();
       }
     },
     /*
     点击消息跳转到指定页面
     */
     handleJumpToPage(SystemMessage){
       if(SystemMessage.messagetype<=4){
         /*
         1. messagetype:
        + 0：文章被收藏
        + 1：收藏的文章更新
        + 2：收藏的文章有新的评论
        + 3：评论被点赞
        + 4: 发表的文章被评论
        + ...(后续可以扩充，比如有新的用户关注，关注的用户发布了新的文章)
         */ 
        if(SystemMessage.messagestatus==0){
          changeMessageStatus(SystemMessage.id).then(response=>{
            if(response.data.status==200){
              // this.readMessageList=[];
              // this.haveReadMessageFlag=true;
              // this,index=0;
              // this.getAllUnreadMessage();
              // this.getAllReadMessage();
            }else{
              this.$message.error(response.data.msg);
            }
          }).catch(error => {
          if (error !== "error") {
            this.$message({
              type: "error",
              message: "更改消息状态失败！",
              showClose: true
            });
          }
        });
        }
        this.$router.push({path:`/view/${SystemMessage.entityid}`})
       }
     }

  }
};
</script>

<style scoped>
.system-message-base-box {
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
.emphasize {
  font-size: 20px;
  font-weight: 600;
}
.el-divider--horizontal{
  margin:3px 0;
}
.el-button--info{
  width: 100%;
}
</style>
