<template>
  <div class="me-view-body" v-title :data-title="title">
    <el-container class="me-view-container">
      <!--<el-aside class="me-area">-->
        <!--<ul class="me-operation-list">-->
          <!--<li class="me-operation-item">-->
            <!--<el-button type="primary" icon="el-icon-edit"></el-button>-->
          <!--</li>-->
        <!--</ul>-->
      <!--</el-aside>-->
      <el-main>

        <div class="me-view-card">
          <h1 class="me-view-title">{{article.title}}</h1>
          <div class="me-view-author">
            <a class="">
              <img class="me-view-picture" :src="article.authoravtar">
            </a>
            <div class="me-view-info">
              <span>{{article.nickname}}</span>
              <div class="me-view-meta">
                <span>{{article.publishtime | format}}</span>
                <span>阅读   {{article.viewcount}}</span>
                <span>评论   {{article.commentcount}}</span>
              </div>

            </div>
            <el-button
              v-if="this.article.authorid == this.$store.state.id"
              @click="editArticle()"
              style="position: absolute;left: 60%;"
              size="mini"
              round
              icon="el-icon-edit">编辑</el-button>
              <el-button
              v-if="this.article.isCollected==false"
              @click="handleCollect()"
              style="position: absolute;left: 65%;"
              size="mini"
              round
              icon="el-icon-star-off">收藏({{article.collectcount}})</el-button>
              <el-button
              v-if="this.article.isCollected==true"
              @click="handleCollect()"
              style="position: absolute;left: 65%;"
              size="mini"
              round
              icon="el-icon-star-on">收藏({{article.collectcount}})</el-button>
          </div>
          
          <div class="me-view-content">
            <markdown-editor :editor=article.editor></markdown-editor>
          </div>

          <div class="me-view-end">
            <el-alert
              title="文章End..."
              type="success"
              center
              :closable="false">
            </el-alert>
          </div>

          <div class="me-view-tag">
            标签：
            <!--<el-tag v-for="t in article.tags" :key="t.id" class="me-view-tag-item" size="mini" type="success">{{t.tagname}}</el-tag>-->
            <!-- <el-button @click="tagOrCategory('tag', t.id)" size="mini" type="primary" v-for="t in article.tags" :key="t.id" round plain>{{t.tagname}}</el-button> -->
            <el-button v-for="t in article.tags" :key="t.id" round plain @click="jumpToTag(t.tagid)">{{t.tagdescription}}</el-button>

          </div>

          <div class="me-view-tag">
            新闻分类：
            <!--<span style="font-weight: 600">{{article.category.categoryname}}</span>-->
            <!-- <el-button @click="tagOrCategory('category', article.category)" size="mini" type="primary" round plain>{{article.category.categoryname}}</el-button> -->
            <el-button size="mini" type="primary" round plain @click="jumpToCategory(article.categoryid)">{{article.categorydescription}}</el-button>
          </div>

          <div class="me-view-comment">
            <div class="me-view-comment-write">
              <el-row :gutter="20">
                <el-col :span="2">
                  <a class="">
                    <img class="me-view-picture" :src="avatar">
                  </a>
                </el-col>
                <el-col :span="22">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2}"
                    placeholder="你的评论..."
                    class="me-view-comment-text"
                    v-model="comment.content"
                    resize="none">
                  </el-input>
                </el-col>
              </el-row>

              <el-row :gutter="20">
                <el-col :span="2" :offset="22">
                  <el-button type="text" @click="publishComment()">评论</el-button>
                </el-col>
              </el-row>
            </div>

            <div class="me-view-comment-title">
              <span>{{article.commentcount}} 条评论</span>
            </div>

            <commment-item
              v-for="(c,index) in comments"
              :comment="c"
              :articleId="article.id"
              :index="index"
              :rootCommentCounts="comments.length"
              @commentCountsIncrement="commentCountsIncrement"
              :key="c.id">
            </commment-item>

          </div>

        </div>
      </el-main>

    </el-container>
  </div>
</template>

<script>
  import MarkdownEditor from '@/components/common/markdown/MarkdownEditor'
  import CommmentItem from '@/components/view/comment/CommentItem'
  import {viewArticle,collectArticle} from '@/api/article'
  import {getCommentsByArticle, publishComment} from '@/api/comment'
  import {getAllCategorys} from '@/api/category'
  import {getAllTags} from '@/api/tag'
  import default_avatar from '@/assets/img/default_avatar.png'

  export default {
    name: 'BlogView',
    created() {
      this.getArticle();

    },
    mounted(){
    },
    watch: {
      '$route': 'getArticle'
    },
    data() {
      return {
        article: {
          id: '',
          title: '',
          commentcount: 0,
          viewcount: 0,
          summary: '',
          authorid:0,
          nickname:"",
          authoravtar:"",
          tags: [],
          categoryid:0,
          categorydescription:"",
          publishtime: '',
          collected:0,
          editor: {
            value: '',
            toolbarsFlag: false,
            subfield: false,
            defaultOpen: 'preview'
          },
          isCollected:false,
          collectcount:0
        },
        comments: [],
        comment: {
          article: {},
          content: ''
        }
      }
    },
    computed: {
      avatar() {
        let avatar = this.$store.state.avatar

        if (avatar.length > 0) {
          return avatar
        }
        return default_avatar
      },
      title() {
        return `${this.article.title} - 文章 - For Fun`
      }
    },
    methods: {
      handleCollect(){
        //收藏或取消收藏文章
        if(this.article.isCollected){
          //已经收藏了该文章,点击按钮表示想取消收藏
          collectArticle(this.$store.state.id,this.article.id,0).then(response=>{
            if(response.data.status==200){
              //取消收藏成功
              this.article.isCollected=false;
              this.article.collectcount=response.data.data;
              this.$message(response.data.msg);
            }else{
              //取消收藏失败
              this.$message(response.data.msg);
            }
          }).catch(error => {
            if (error !== 'error') {
              that.$message({type: 'error', message: '文章取消收藏失败', showClose: true})
            }
          });
        }else{
          //未收藏该文章,点击按钮表示想收藏
          collectArticle(this.$store.state.id,this.article.id,1).then(response=>{
            if(response.data.status==200){
              //收藏成功
              this.article.isCollected=true;
              this.article.collectcount=response.data.data;
              this.$message(response.data.msg);
            }else{
              //收藏失败
              this.$message(response.data.msg);
            }
          }).catch(error => {
            if (error !== 'error') {
              that.$message({type: 'error', message: '文章收藏失败', showClose: true})
            }
          });
        }
        this.article.isCollected=!this.article.isCollected;
      },
      tagOrCategory(type, id) {
        this.$router.push({path: `/${type}/${id}`})
      },
      editArticle() {
        this.$router.push({path: `/write/${this.article.id}`})
      },
      getArticle() {
        let that = this
        viewArticle(that.$route.params.id).then(data=> {
          console.log(data.data.data)
          Object.assign(that.article, data.data.data)
          if(that.article.collected==0){
            this.article.isCollected=false;
          }else{
            this.article.isCollected=true;
          }
          that.article.editor.value = data.data.data.content
          that.getCommentsByArticle()
        }).catch(error => {
          if (error !== 'error') {
            console.log(error)
            that.$message({type: 'error', message: '文章加载失败', showClose: true})
          }
        });
      },
      publishComment() {
        let that = this
        if (!that.comment.content) {
          return;
        }
        let commentdata={
          articleid:that.article.id,
          authorid:that.$store.state.id,
          content:that.comment.content
        };
        publishComment(commentdata).then(data => {
          console.log(data.data.data)
          that.$message({type: 'success', message: '评论成功', showClose: true})
          that.comments.unshift(data.data.data)
          that.commentCountsIncrement()
          that.comment.content = ''
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '评论失败', showClose: true})
          }
        })
      },
      getCommentsByArticle() {
        let that = this
        getCommentsByArticle(this.article.id).then(data => {
          that.comments = data.data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '评论加载失败', showClose: true})
          }
        })
      },
      commentCountsIncrement() {
        this.article.commentcount += 1
      },
      jumpToCategory(id){
        this.$router.push({path: '/articles/category/'+id});
      },
      jumpToTag(id){
        this.$router.push({path: '/articles/tag/'+id});
      }
    },
    components: {
      'markdown-editor': MarkdownEditor,
      CommmentItem
    },
    //组件内的守卫 调整body的背景色
    beforeRouteEnter(to, from, next) {
      window.document.body.style.backgroundColor = '#fff';
      next();
    },
    beforeRouteLeave(to, from, next) {
      window.document.body.style.backgroundColor = '#f5f5f5';
      next();
    }
  }
</script>

<style>
  .me-view-body {
    margin: 100px auto 140px;
  }

  .me-view-container {
    width: 1100px;
  }

  .el-main {
    overflow: hidden;
  }

  .me-view-title {
    font-size: 34px;
    font-weight: 700;
    line-height: 1.3;
  }

  .me-view-author {
    /*margin: 30px 0;*/
    margin-top: 30px;
    vertical-align: middle;
  }

  .me-view-picture {
    width: 40px;
    height: 40px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #5fb878;
  }

  .me-view-info {
    display: inline-block;
    vertical-align: middle;
    margin-left: 8px;
  }

  .me-view-meta {
    font-size: 12px;
    color: #969696;
  }

  .me-view-end {
    margin-top: 20px;
  }

  .me-view-tag {
    margin-top: 20px;
    padding-left: 6px;
    border-left: 4px solid #c5cac3;
  }

  .me-view-tag-item {
    margin: 0 4px;
  }

  .me-view-comment {
    margin-top: 60px;
  }

  .me-view-comment-title {
    font-weight: 600;
    border-bottom: 1px solid #f0f0f0;
    padding-bottom: 20px;
  }

  .me-view-comment-write {
    margin-top: 20px;
  }

  .me-view-comment-text {
    font-size: 16px;
  }

  .v-show-content {
    padding: 8px 25px 15px 0px !important;
  }

  .v-note-wrapper .v-note-panel {
    box-shadow: none !important;
  }

  .v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
    background: #fff !important;
  }


</style>
