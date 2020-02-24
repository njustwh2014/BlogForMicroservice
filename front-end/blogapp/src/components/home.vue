<template>
  <div id="home">
    <el-container>
    	
    	<baseheader :activeIndex="activeIndex"></baseheader>
		  
		  <router-view class="me-container"/>
		  
			<base-footer v-show="footerShow"></base-footer>
		  
		</el-container>
		
  </div>
  
</template>

<script>
import basefooter from '@/components/basefooter'
import baseheader from '@/components/baseheader'

export default {
  name: 'home',
  data (){
  	return {
  			activeIndex: '/',
  			footerShow:false
  	}
	},
  components:{
  	'baseheader':baseheader,
  	'base-footer':basefooter
  },
  beforeRouteEnter (to, from, next){
  	 next(vm => {
			vm.activeIndex = to.path
  	})
  },
  beforeRouteUpdate (to, from, next) {
		console.log(to.path)
	  if(to.path == '/'){
	  	this.footerShow = true
	  }else{
	  	this.footerShow = false
	  }
		this.activeIndex = to.path
	  next()
	}
}
</script>

<style>
.me-container{
  margin: 100px auto 140px;
	margin-left: 10%;
	margin-right: 10%
}
</style>
