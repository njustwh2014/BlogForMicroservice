<template>
  <el-header class="me-area">
    <el-row class="me-header">
      <el-col :span="4" class="me-header-left" align="center">
        <router-link to="/" class="me-title">
          <img src="static/logo.png">
        </router-link>
      </el-col>

      <el-col v-if="!simple" :span="14">
        <el-menu
          :router="true"
          menu-trigger="hover"
          active-text-color="#5FB878"
          :default-active="activeIndex"
          mode="horizontal"
        >
          <el-menu-item index="/">首页</el-menu-item>
          <el-submenu index="/category">
            <template slot="title">分类</template>
            <el-menu-item
              v-for="a in allcategory"
              :key="a.id"
              :index="generateIndex(a.id)"
            >{{a.categorydescription}}</el-menu-item>
          </el-submenu>
          <!-- <el-menu-item index="3" disabled>步行街</el-menu-item> -->
          <!-- <el-menu-item index="4" disabled>待开发</el-menu-item> -->
          
          
          <template v-if="!user.login">
          </template>
          <template v-else>
            <el-menu-item @click="jumpToUserInfo(user.id)">
              <i class="el-icon-user"></i>个人中心
            </el-menu-item>
            <el-menu-item index="/write">
              <i class="el-icon-edit"></i>发布新闻
            </el-menu-item>
          </template>
          
          <!-- <el-menu-item index="/tag/all">标签</el-menu-item>
          <el-menu-item index="/archives">文章归档</el-menu-item>
          <el-menu-item index="/log">日志</el-menu-item>
          <el-menu-item index="/messageBoard">留言板</el-menu-item>-->
          <el-col :span="5" :offset="1">
            <div style="margin-top: 10px;">
              <el-input
                placeholder="请输入搜索内容"
                v-model="searchData"
                class="input-with-select"
                autofocus="true"
              >
                <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
              </el-input>
            </div>
          </el-col>
        </el-menu>
      </el-col>
      <template v-else :span="14">
        <slot></slot>
      </template>
      <el-col :span="6">
        <el-menu :router="true" menu-trigger="hover" mode="horizontal" active-text-color="#5FB878">
          <!-- <template v-if="!user.login">  -->
          <template v-if="!user.login">
            <el-menu-item index="/login">
              <el-button type="text">登录</el-button>
            </el-menu-item>
            <el-menu-item index="/regist">
              <el-button type="text">注册</el-button>
            </el-menu-item>
          </template>
          <template v-else>
            <el-menu-item>
              <el-badge :value="unreadMessagecount" :max="10" class="item" :hidden="badgeFlag">
                <el-popover placement="bottom" width="400" trigger="hover" class="popover-item">
                  <div>
                    共有{{unreadMessagecount}}条未读消息
                    <el-divider></el-divider>
                    <div v-for="c in unreadMessageList" :key="c.id" class="text">
                      <span @click="handleJumpToPage(c)"><b>{{c.messagedate|format}}:</b>&nbsp;{{c.messagecontent}}</span>
                      <el-divider></el-divider>
                    </div>
                    <el-button type="text" @click="handleGetAllMessage()">
                      <i class="el-icon-more-outline"></i>&nbsp;显示全部消息
                    </el-button>
                  </div>

                  <el-button slot="reference" type="text">
                    <i class="el-icon-bell"></i>
                  </el-button>
                </el-popover>
              </el-badge>
            </el-menu-item>
            <el-submenu index>
              <template slot="title">
                <img class="me-header-picture" :src="user.avatar">
              </template>
              <el-menu-item @click="logout">
                <i class="el-icon-back"></i>退出
              </el-menu-item>
            </el-submenu>
          </template>
        </el-menu>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
import { getAllCategorys } from "@/api/category";
import { getMessageStatus,changeMessageStatus } from "@/api/message";
export default {
  name: "baseheader",
  props: {
    activeIndex: String,
    simple: {
      type: Boolean,
      default: false
    }
  },
  created() {
    this.getCategorys();
  },
  update(){
    let userid=this.$store.state.id;
    if(userid!=""){
      this.validateHaveUnReadMessage();
    }
  },
  data() {
    return {
      allcategory: [{ id: 1, categorydescription: "时政" }],
      searchData: "",
      badgeFlag: true,
      unreadMessagecount: 0,
      unreadMessageList:[],
    };
  },
  computed: {
    user() {
      let login = this.$store.state.email.length != 0;
      let avatar = this.$store.state.avatar;
      let id=this.$store.state.id;
      if (login) {
        this.validateHaveUnReadMessage();
      }
      return {
        login,
        avatar,
        id
      };
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
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
              this.validateHaveUnReadMessage();
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
     },
    logout() {
      let that = this;
      this.$store
        .dispatch("logout")
        .then(() => {
          this.$router.push({ path: "/" });
        })
        .catch(error => {
          if (error !== "error") {
            that.$message({ message: error, type: "error", showClose: true });
          }
        });
    },
    search() {
      let searchData = this.searchData;
      if (searchData != "") {
        this.searchData = "";
        this.$router.push({ path: `/search/${searchData}` });
      } else {
        this.$message("不要调戏人家啦！");
      }
    },
    generateIndex(id) {
      return "/articles/category/" + id;
    },
    getCategorys() {
      getAllCategorys()
        .then(data => {
          this.allcategory = data.data.data;
        })
        .catch(error => {
          if (error !== "error") {
            that.$message({
              type: "error",
              message: "文章分类加载失败",
              showClose: true
            });
          }
        });
    },
    validateHaveUnReadMessage() {
      let userid = this.$store.state.id;
      if (userid != "") {
        getMessageStatus(userid).then(response => {
          if(response.data.status==200){
            this.unreadMessagecount=response.data.data.total;
            if(this.unreadMessagecount==0){
              this.badgeFlag=true;
            }else{
              this.unreadMessageList=response.data.data.messagelist;
              this.badgeFlag=false;
            }
          }else{
            this.$message.error(response.data.msg);
          }
        });
      }
    },
    handleGetAllMessage(){
      this.$router.push({ path: "/systemmessage" });
    },
    jumpToUserInfo(userid){
      this.$router.push({path:`/userinfo/${userid}`})
    }


  },
  directives: {
    focus: {
      bind(el) {
        // 在元素绑定时执行，只执行一次
        //还没有插入到dom树中，只在内存中
      },
      inserted(el) {
        // 表示元素插入到dom中会执行，也执行一次
        el.focus();
      },
      updated(el) {
        // 当组建更新的时候执行，科能会触发多次
      }
    }
  }
};
</script>

<style scoped>
.el-header {
  position: fixed;
  z-index: 1024;
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0, 0%, 7%, 0.1), 0 0 0 1px hsla(0, 0%, 7%, 0.1);
}

.me-title {
  margin-top: 10px;
  font-size: 24px;
}

.me-header-left {
  margin-top: 5px;
}

.me-title img {
  max-height: 3.5rem;
  max-width: 100%;
}

.me-header-picture {
  width: 36px;
  height: 36px;
  border: 1px solid #ddd;
  border-radius: 50%;
  vertical-align: middle;
  background-color: #ffffff;
}
.text {
  font-size: 16px;
}

.el-icon-bell {
  margin-top: -11px;
  font-size: 250%;
}
.item {
  margin-top: 6px;
  /* margin-right: 20px; */
}
.popover-item {
}

.el-divider--horizontal{
  margin:3px 0;
}
</style>
