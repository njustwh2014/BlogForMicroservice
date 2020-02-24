<template>
  <div class="me-category-article">
    <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
    <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item> 
    </scroll-page>
  </div> 
</template>


<script>
  import {getArticlesByCategory, getArticlesByTag} from '@/api/article'
  import {getArticles} from '@/api/article'
  import articleItem from '@/components/common/articleItem'
  import scrollPage from '@/components/common/scrollPage'

  export default {
    name: 'BlogCategoryTag',
    props:{
    offset:{
      type:Number,
      default:100
    },
    page:{
      type:Object,
      default(){
        return {}
      }
    },
    query:{
      type:Object,
      default(){
        return{}
      }
    }
  },
  watch:{
    'query':{
      handler(){
        this.noData=false
        this.articles=[]
        this.innerPage.pageNumber=1
        this.getArticlesByCategoryOrTag()
      },
      deep:true
    },
    'page':{
      handler(){
        this.noData=false
        this.articles=[]
        this.innerPage=this.page
        this.getArticlesByCategoryOrTag()
      }
    },
    '$route' (to, from) { //监听路由是否变化
    if(this.$route.params.id){// 判断条件1  判断传递值的变化
      //获取文章数据
      this.loading=false;
      this.noData=false;
      this.innerPage={
        pageSize:5,
        pageNumber:1,
        name:'a.createDate',
        sort:'desc'
      };
      this.articles=[
        // {id:1,weight:1,title:"湖人总冠军",commentcount:12,viewcount:13,summary:"怎么可能",author:{nickname:"james"},tags:[{tagdescription:"jrs"},{tagdescription:"篮球"},{tagdescription:"lakers"}],createDate:"2019-03-01 00:00:00"}
      ];
      this.getArticlesByCategoryOrTag()
    }
  } 
    
  },
  created(){
    this.getArticlesByCategoryOrTag()
  },
  data(){
    return{
      loading:false,
      noData:false,
      innerPage:{
        pageSize:5,
        pageNumber:1,
        name:'a.createDate',
        sort:'desc'
      },
      articles:[
        // {id:1,weight:1,title:"湖人总冠军",commentcount:12,viewcount:13,summary:"怎么可能",author:{nickname:"james"},tags:[{tagdescription:"jrs"},{tagdescription:"篮球"},{tagdescription:"lakers"}],createDate:"2019-03-01 00:00:00"}
      ]
    }
  },
  methods:{
    load(){
      this.getArticlesByCategoryOrTag()
    },
    view(id){
      this.$router.push({path: `/view/${id}`})
    },

    getArticlesByCategoryOrTag(){
      let type=this.$route.params.type;
      let id=this.$route.params.id;
      this.loading=true;
      if(type=='category'){
        getArticlesByCategory(this.innerPage,id).then(data=>{
          let newArticles = data.data.data;
          if (newArticles && newArticles.length > 0) {
            this.innerPage.pageNumber += 1
            this.articles = this.articles.concat(newArticles)
          } else {
            this.noData = true
          }
        }).catch(error => {
          if (error !== 'error') {
            
            this.$message({type: 'error', message: '文章加载失败!', showClose: true})
          }
        }).finally(() => {
          this.loading = false
        })
      }else{
        getArticlesByTag(this.innerPage,id).then(data=>{
          console.log(data.data.data)
          let newArticles = data.data.data;
          if (newArticles && newArticles.length > 0) {
            this.innerPage.pageNumber += 1
            this.articles = this.articles.concat(newArticles)
          } else {
            this.noData = true
          }
          console.log("ok");
        }).catch(error => {
          if (error !== 'error') {
            
            this.$message({type: 'error', message: '文章加载失败!', showClose: true})
          }
        }).finally(() => {
          this.loading = false
        })

      }
    }
  },
    components: {
      'scroll-page':scrollPage,
      'article-item':articleItem
    }
  }
</script>

<style scoped>
  .me-category-article{
    margin-left:20%;
    width:50%;
  }
  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 10px;

  }

</style>
