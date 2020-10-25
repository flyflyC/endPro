<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-button type="text" @click="dialogChapterFormVisible = true">添加章节</el-button>
    <!-- 章节 -->
    <ul class="chanpterList">
      <li
        v-for="chapter in chapterNestedList"
        :key="chapter.id">
        <p>
          {{ chapter.title }}

          <span class="acts">
                <el-button type="text" @click="dialogVideoFormVisible = true, chapterId = chapter.id">添加课时</el-button>
                <el-button type="text" @click="editChapter(chapter.id)">编辑</el-button>
                <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
            </span>
        </p>

        <!-- 视频 -->
        <ul class="chanpterList videoList">
          <li
            v-for="video in chapter.children"
            :key="video.id">
            <p>{{ video.title }}
              <span class="acts">
                        <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                        <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                    </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.free">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import chapter from '@/api/chapter/chapter'
  import video from '@/api/video/video'

  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        courseId: '', // 所属课程,为上一步传过来的id
        chapterNestedList: [], // 章节嵌套课时列表
        dialogChapterFormVisible: false, //是否显示章节表单
        chapter: {// 章节对象
          title: '',
          sort: 0,
          courseId: ''
        },
        saveVideoBtnDisabled: false, // 课时按钮是否禁用
        dialogVideoFormVisible: false, // 是否显示课时表单
        chapterId: '', // 课时所在的章节id
        video: {// 课时对象
          title: '',
          sort: 0,
          free: 0,
          videoSourceId: ''
        }
      }
    },

    created() {
      console.log('chapter created')
      this.init()
    },

    methods: {
      init() {
        if (this.$route.params && this.$route.params.id) {
          this.courseId = this.$route.params.id // 获取课程id
          // 根据id获取课程基本信息
          this.fetchChapterNestedListByCourseId()
        }
      },
      //#############################小节管理##################################
      saveOrUpdateVideo() { // 确定按钮的保存或更新方法
        this.saveVideoBtnDisabled = true
        if (!this.video.id) {
          this.saveDataVideo() // 如果id为空则执行保存
        } else {
          this.updateDataVideo() // 否则执行更新操作
        }
      },

      saveDataVideo() {
        this.video.courseId = this.courseId // 设置课程id
        this.video.chapterId = this.chapterId // 设置章节id
        video.saveVideoInfo(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.helpSaveVideo() // 执行关闭等相关操作
        })
      },
      updateDataVideo() {
        video.updateVideoInfoById(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.helpSaveVideo()
        })
      },
      helpSaveVideo() {
        this.dialogVideoFormVisible = false// 如果保存成功则关闭对话框
        this.fetchChapterNestedListByCourseId()// 刷新列表
        this.video.title = ''// 重置章节标题
        this.video.sort = 0// 重置章节标题
        this.video.videoSourceId = ''// 重置视频资源id
        this.saveVideoBtnDisabled = false
      },
      editVideo(videoId) {
        this.dialogVideoFormVisible = true
        video.getVideoInfoById(videoId).then(response => {
          this.video = response.data.item
        })
      },
      removeVideo(videoId) {
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', { // 删除要进行确认操作
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => { // 确定后才进行删除操作
          return video.deleteVideoInfoById(videoId)
        }).then(() => { // 删除成功要重新进行列表的刷新
          this.fetchChapterNestedListByCourseId()// 刷新列表，该列表为嵌套列表
          this.$message({ // 同时添加成功信息
            type: 'success',
            message: '删除成功!'
          })
        }).catch((response) => { // 失败
          if (response === 'cancel') { // 如果删除失败，该框架会进行统一拦截并输出
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          }
        })
      },
      //#############################章节管理##################################
      fetchChapterNestedListByCourseId() {
        chapter.getNestedTreeList(this.courseId)
          .then(response => {
            this.chapterNestedList = response.data.items
          })
      },
      previous() {
        console.log('previous')
        this.$router.push({ path: '/edu/course/info/' + this.courseId })
      },

      next() {
        console.log('next')
        this.$router.push({ path: '/edu/course/publish/' + this.courseId })
      },
      saveOrUpdate() { // 新增或修改章节方法
        this.saveBtnDisabled = true // 防止表单多次提交
        if (!this.chapter.id) {
          this.saveData()
        } else {
          this.updateData()
        }
      },

      saveData() { // 新增章节方法
        this.chapter.courseId = this.courseId
        chapter.save(this.chapter).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.helpSave()
        }).catch((response) => {
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },

      updateData() {
        chapter.updateById(this.chapter).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.helpSave()
        }).catch((response) => {
          // console.log(response)
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },

      helpSave() { // 操作成功后的其余辅助操作
        this.dialogChapterFormVisible = false// 如果保存成功则关闭对话框
        this.fetchChapterNestedListByCourseId()// 刷新列表
        this.chapter.title = ''// 重置章节标题
        this.chapter.sort = 0// 重置章节标题
        this.saveBtnDisabled = false
      },
      editChapter(chapterId) {
        this.dialogChapterFormVisible = true
        chapter.gteById(chapterId)
          .then(response => {
            this.chapter = response.data.item
          })
      },
      removeChapter(chapterId) {
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return chapter.removeById(chapterId)
        }).then(() => {
          this.fetchChapterNestedListByCourseId()// 刷新列表
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch((response) => { // 失败
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          } else {
            this.$message({
              type: 'error',
              message: response.message
            })
          }
        })
      }
    }
  }
</script>

<style scoped>
  .chanpterList {
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .chanpterList li {
    position: relative;
  }

  .chanpterList p {
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #DDD;
  }

  .chanpterList .acts {
    float: right;
    font-size: 14px;
  }

  .videoList {
    padding-left: 50px;
  }

  .videoList p {
    float: left;
    font-size: 14px;
    margin: 10px 0;
    padding: 10px;
    height: 50px;
    line-height: 30px;
    width: 100%;
    border: 1px dotted #DDD;
  }

</style>
