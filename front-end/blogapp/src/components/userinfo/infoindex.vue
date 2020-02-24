<template>
<el-container class="me-info-container">
  <base-side :activeIndex="activeIndex"></base-side>
  <el-main class='me-info-main'>
  <router-view />
  </el-main>
</el-container>
</template>


<script>
import baseSide from '@/components/userinfo/baseSide'
export default {
  data (){
  return {
      activeIndex: '/userinfo',
      footerShow:true
  }
  },
  components:{
    'base-side':baseSide
  },
  beforeRouteEnter (to, from, next){
    next(vm => {
    vm.activeIndex = to.path
    })
  },
  beforeRouteUpdate (to, from, next) {
	  if(to.path == '/userinfo'){
	  	this.footerShow = true
	  }else{
	  	this.footerShow = false
	  }
	  this.activeIndex = to.path
	  next()
	}
};
</script>

<style scoped>  
  .el-aside {
    color: #5fb878;
  }
  .me-info-container{
    /* height: 500px;  */
    margin: 65px auto 90px;
  }
  .me-info-main{
    margin: 0px auto 90px;
    padding: 0px;
  }

</style>
