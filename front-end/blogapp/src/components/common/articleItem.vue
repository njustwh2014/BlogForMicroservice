<template>
  <el-card class="me-area" :body-style="{padding:'16x'}">
    <div class="me-article-header">
      <a @click="view(id)" class="me-article-title">{{title}}</a>
      <el-button v-if="weight>0" class="me-article-icon" type="text">置顶</el-button>
      <span class="me-pull-right me-article-count">
        <i class="me-icon-comment"></i>&nbsp;{{commentcount}}
      </span>
      <span class="me-pull-right me-article-count">
        <i class="el-icon-view"></i>&nbsp;{{viewcount}}
      </span>
      <span class="me-pull-right me-article-count">
        <!-- <i class="el-icon-star-off"></i>&nbsp;{{collectcount}} -->
        <i class="el-icon-star-off"></i>&nbsp;{{collectcount}}
      </span>
    </div>
    <div class="me-article-description">
      {{summary}}
    </div>
    <div class="me-article-footer">
      <span class="me-article-author">
        <i class="el-icon-view"></i>&nbsp;{{nickname}}
      </span>

      <el-tag v-for="t in tags" :key="t.tagdescription" size="mini" type="success" @click="jumpToTag(t.tagid)">{{t.tagdescription}}</el-tag>
      <span class="me-pull-right me-article-count">
        <i class="el-icon-time"></i>&nbsp;{{publishtime|format}}
        <!-- 后面对时间进行格式化 -->
      </span>
    </div>
  </el-card>
</template>

<script>
export default {
  name:'articleItem',
  props:{
    // 注意接口
    id:Number,
    weight:Number,
    title:String,
    commentcount:Number,
    viewcount:Number,
    collectcount:Number,
    summary:String,
    // author:Object,
    userid:Number,
    nickname:String,
    tags:Array,
    publishtime:String
  },
  data(){
    return {}
  },
  methods:{
    view(id){
      this.$router.push({path:`/view/${id}`})
    },
    jumpToTag(id){
      this.$router.push({path: '/articles/tag/'+id})
    }
  }
}
</script>
<style scoped>

 .me-article-header {
    /*padding: 10px 18px;*/
    padding-bottom: 10px;
  }

  .me-article-title {
    font-weight: 600;
  }

  .me-article-icon {
    padding: 3px 8px;
  }

  .me-article-count {
    color: #a6a6a6;
    padding-left: 14px;
    font-size: 13px;
  }

  .me-pull-right {
    float: right;
  }

  .me-artile-description {
    font-size: 13px;
    line-height: 24px;
    margin-bottom: 10px;
  }

  .me-article-author {
    color: #a6a6a6;
    padding-right: 18px;
    font-size: 13px;
  }

  .el-tag {
    margin-left: 6px;
  }

</style>
