<template>
  <el-carousel indicator-position="outside" class="me-carousel">
    <el-carousel-item v-for="item in articles" :key="item.id">
      <el-card :body-style="{ padding: '0px' }">
      <!-- 图片长宽比:1964/1160 -->
      <img :src="item.cover" class="image" @click="view(item.articleid)" height="250" width="100%">
      <div style="padding: 10px;">
        <span style="font-size:20px;">{{item.title}}</span>
        <!-- <div class="bottom clearfix">
          <time class="time">{{ currentDate }}</time>
          <el-button type="text" class="button">操作按钮</el-button>
        </div> -->
      </div>
    </el-card>
    </el-carousel-item>
  </el-carousel>
</template>


<script>
export default {
  name:'carousel',
  props: {
      articles: {
        type: Array,
        required: true
      }
    },
  data() {
    return {
      imgLists:[{
        id:1,imgSrc:"http://47.100.58.57:3000/前端开发.jpg",desc:"前端开发"
      },
      {
        id:2,imgSrc:"http://47.100.58.57:3000/后端开发.jpg",desc:"后端开发"
      },
      {
        id:3,imgSrc:"http://47.100.58.57:3000/数据库.jpg",desc:"数据库"
      },
      {
        id:4,imgSrc:"http://47.100.58.57:3000/服务器.jpg",desc:"服务器"
      },
      {
        id:5,imgSrc:"http://47.100.58.57:3000/机器学习.jpg",desc:"机器学习"
      }
      ]
    };
  },
  methods:{
    view(id) {
        this.$router.push({path: `/view/${id}`})
      }
  }
}
</script>
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    /* width: 100%; */
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
  .me-carousel{
    height:330px;
  }
</style>