<template>
  <div>
    <div class="vblog">
      <h2>{{blog.title}}</h2>
        <i class="el-icon-delete" @click="removeDataById(blog.id)"></i>
      <el-divider></el-divider>
      <div  class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
  import 'github-markdown-css'
  import blog from "@/api/blog/blog";
    export default {
      name: "detail",
      data(){
        return{
          blog:{
            id:"",
            title: "",
            content: ""
          }
        }
      },
      created() {
        this.init()
      },
      methods: {
        init() {
          //判断路径有id值,做修改
          if (this.$route.params && this.$route.params.id) {
            //从路径获取id值
            const id = this.$route.params.id
            //调用根据id查询的方法
            this.getById(id)
          } else { //路径没有id值，做添加
            //清空表单
            this.blog = {}
          }
        },
        getById(id) {
          const blogId = this.$route.params.id
          console.log(blogId)
          blog.detail(blogId).then(res => {
            console.log(res)
            this.blog = res.data.blog
            this.blog.id = res.data.blog.id
            this.blog.title = res.data.blog.title
            var MarkdownIt=require("markdown-it")
            var md=new MarkdownIt()
            var result = md.render(res.data.blog.content)
            this.blog.content = result
            console.log(this.blog.content)
          })
        },
        removeDataById(id) {
          this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {  //点击确定，执行then方法
            //调用删除的方法
            blog.deleteBlogById(id)
              .then(response =>{//删除成功
                //提示信息
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                //回到列表页面
              })
          }) //点击取消，执行catch方法
        }

      }
    }
</script>

<style scoped>
  .vblog{
    box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
    width: 100%;
    min-height: 700px;
    padding: 20px 15px;
  }
</style>
