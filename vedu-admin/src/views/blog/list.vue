<!--<template>-->
<!--  <div class="mcontaner">-->
<!--    <div class="block">-->
<!--      <el-form :inline="true" class="demo-form-inline">-->
<!--        <el-form-item>-->
<!--          <el-input v-model="queryBlog.title" placeholder="标题"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item>-->
<!--          <el-input v-model="queryBlog.userId" placeholder="作者"/>-->
<!--        </el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>-->
<!--        <el-button type="default" @click="resetData()">清空</el-button>-->
<!--      </el-form>-->
<!--    </div>-->

<!--      <el-timeline>-->
<!--        <el-timeline-item :timestamp="blog.created"  placement="top" v-for="blog in list">-->
<!--          <el-card>-->
<!--            <h4>-->
<!--              <router-link :to="'/blog/detail/'+blog.id">-->
<!--                {{blog.title}}-->
<!--              </router-link>-->
<!--            </h4>-->
<!--            <p>{{blog.description}}</p>-->
<!--          </el-card>-->
<!--        </el-timeline-item>-->
<!--      </el-timeline>-->
<!--      <el-pagination class="bpage"-->
<!--                     background-->
<!--                     layout="total,prev,pager,next,jumper"-->
<!--                     :current-page="page"-->
<!--                     :page-size="limit"-->
<!--                     :total="total"-->
<!--                     @current-change="getList">-->
<!--      </el-pagination>-->

<!--  </div>-->

<!--</template>-->
<template>
  <div class="app-container">
    博客列表

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="queryBlog.username" placeholder="作者"/>
      </el-form-item>
      <el-form-item>
        <el-input v-model="queryBlog.title" placeholder="标题"/>
      </el-form-item>


      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="50"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="username" label="作者" width="80" />

      <el-table-column prop="title" label="标题" width="200" />

      <el-table-column label="发布状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status===1?'已发布':'未发布' }}
        </template>
      </el-table-column>

      <el-table-column prop="description" label="描述" width="300" />
      <!--<el-table-column prop="content" label="内容" />-->
      <el-table-column prop="gmtCreate" label="发布时间" width="160"/>
      <el-table-column prop="gmtModified" label="更新时间" width="160"/>
      <el-table-column prop="views" label="浏览数" width="40" />
      <el-table-column prop="goods" label="点赞数" width="40" />
      <el-table-column prop="comments" label="评论数" width="40" />

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <router-link :to="'/blog/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <router-link :to="'/blog/detail/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-search">查看</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />

  </div>
</template>
<script>
//引入调用blog.js文件
import blog from "@/api/blog/blog";
    export default {
      data() {//定义变量和初始值
        return {
          list: {},//查询之后接口返回集合
          page: 1,//当前页
          limit: 3,//每页记录数
          total: 0,//总记录数
          queryBlog: {
            username:"",
            title:"",
          }, //条件封装对象
        }
      },
      created() {//页面渲染之前执行
        this.getList(1)
      },
      methods: {//创建具体的方法，调用blog.js定义的方法
        getList(page=1) {
          this.page=page
          console.log(page)
          blog.getBlogListPage(this.page, this.limit, this.queryBlog)
            .then(response => {//请求成功
              //response接口返回的数据
              //console.log(response)
              this.total=response.data.total
              this.list=response.data.rows
              console.log(this.list)
              console.log(this.total)
            })
          .catch(error=>{
            console.log(error)
          })//请求失败
        },
        resetData() {//清空的方法
          //表单输入项数据清空
          this.queryBlog= {}
          //查询所有讲师数据
          this.getList()
        }
      }
    }
</script>

<style scoped>

  .block{
    margin: 0 auto;
    text-align: center;
  }

  .bpage{
    margin: 0 auto;
    text-align: center;
  }

</style>
