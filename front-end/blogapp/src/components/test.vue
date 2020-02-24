<template>
<el-upload
  class="upload-demo"
  action="https://localhost/:8080/upload"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :before-upload="handleUpload"
  :on-success="handleSuccess"
  :on-change="handleChange"
  :file-list="fileList2"
  :http-request="uploadSectionFile"
  list-type="picture-card">
  <i class="el-icon-plus"></i>
</el-upload>
</template>

<script>
import {upload} from '@/api/upload'
import request from '@/request'
export default {
    data() {
      return {
        myHeader:{'Content-Type': 'multipart/form-data'},
        fileList2: []
      };
    },
    methods: {
      uploadSectionFile(param){
        let that = this
        let formdata = new FormData();
        formdata.append('editormd-image-file', param.file);
        upload(formdata).then(data => {
          console.log(data.data.url)
          // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
          if (data.data.success == 1 ) {

            that.fileList2.concat({name:param.name,url:data.data.url});
          } else {
            that.$message({message: data.msg, type: 'error', showClose: true})
          }

        }).catch(err => {
          that.$message({message: err, type: 'error', showClose: true});
        })
      },
      handleUpload(file){
        console.log(file);
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleChange(file, fileList){
        console.log("12",file, fileList);
      },
      handleSuccess(response, file, fileList){
        console.log("11",response,file, fileList);

      }
    }
  }
</script>

<style scoped>


</style>
