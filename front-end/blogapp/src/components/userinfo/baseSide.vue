<template>
  <el-aside class="me-info-aside">
    <el-menu :default-active="activeIndex" :router=true :unique-opened=true active-text-color="#5FB878">
      <el-menu-item index="/userinfo">
        <template slot="title"><i class="el-icon-user"></i>基本资料</template>
      </el-menu-item>
      <el-menu-item index="/systemmessage">
        <template slot="title"><i class="el-icon-message"></i>系统消息</template>
      </el-menu-item>
      <!-- <el-menu-item index="/following">
        <template slot="title"><i class="el-icon-message"></i>我关注的人</template>
      </el-menu-item> -->
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>导航二</template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>导航三</template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="3-1">选项1</el-menu-item>
          <el-menu-item index="3-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="3-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>


<script>
export default {
  name:'baseSide',
  props: {
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },

  data() {
      return {
        activeIndex1: '1',
        activeIndex2: '1'
      }
    },
  

}
</script>

<style scoped>
.me-info-aside{
    width: 400px;
    background-color: rgb(238, 241, 246)
  }
</style>