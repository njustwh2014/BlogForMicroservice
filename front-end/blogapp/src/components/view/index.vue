<template>
  <el-container>
    <el-main>
      <article-scroll-page></article-scroll-page>
    </el-main>
    <el-aside style="width:450px;">
      <carousel :articles="carouselArticles"></carousel>
      <cardme class="me-area"></cardme>
      <card-article cardHeader="更多最热文章" :articles="listArticles"></card-article>
    </el-aside>
  </el-container>
</template>
<script>
import cardme from '@/components/view/card/cardme.vue'
import ArticleScrollPage from '@/components/view/article/ArticleScrollPage.vue'
import CardAticle from '@/components/view/card/CardArticle.vue'
import {getHotArticles} from '@/api/article'
import carousel from '@/components/common/carousel/carousel.vue'
export default {
  name:'index',
  created(){
    this.GetHotArticles();
  },
  data(){
    return{
      carouselArticles:[],
      listArticles:[]
    }  
  },
  methods:{
    GetHotArticles(){
      getHotArticles().then(data=>{
        let hotArticles=data.data.data;
        let count=0;
        for(let item of hotArticles){
          if(item.cover!="" && count<5){
            this.carouselArticles.push(item);
            count=count+1;
          }else{
            this.listArticles.push(item);
          }
        }
      }).catch(error=>{
        if('error'!==error){
          this.message({type: 'error', message: '最热文章加载失败!', showClose: true});
        }
      })
    }
  },
  components:{
    'cardme':cardme,
    ArticleScrollPage,
    'card-article':CardAticle,
    carousel
  }
}
</script>
<style scoped>
 .el-container {
    width: 100px;
  }

  .el-aside {
    margin-left: 20px;
    width: 40%;
  }

  .el-main {
    padding: 0px;
    line-height: 16px;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
</style>


