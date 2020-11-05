<template>
  <div class="m-content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要" prop="description">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <mavon-editor v-model="ruleForm.content"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
  import blogApi from "@/api/blog/blog";
    export default {
      data() {
        return {
          ruleForm: {
            title: '',
            description: '',
            content: ''
          },
          blogId:'',
          rules: {
            title: [
              { required: true, message: '请输入标题', trigger: 'blur' },
              { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
            ],
            description: [
              { required: true, message: '请输入摘要', trigger: 'blur' }
            ],
            content: [
              { required: true, message: '请输入内容', trigger: 'blur' }
            ]
          }
        };
      },
      created() {
        this.init()
      },
      methods: {
        // 初始化方法
        init() {
          if (this.$route.params && this.$route.params.id) {// 如果请求地址带有id，则为修改页面
            this.blogId = this.$route.params.id
            //根据id获取博客基本信息
            this.fetchBlogInfoById(this.blogId)// 根据id进行数据重新请求
          }
        },
        fetchBlogInfoById(id){
          blogApi.detail(id)
          .then(res=>{
            this.ruleForm.title=res.data.blog.title;
            this.ruleForm.description=res.data.blog.description;
            this.ruleForm.content=res.data.blog.content
          })
        },
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              const _this=this
              this.$axios.post('/Eduservice/blog/add',this.ruleForm,{

              }).then(res=>{
                console.log(res)
              })
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>

</style>
