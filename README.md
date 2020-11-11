# endPro
微学院在线教育
tyuuii
项目成员更改
cc更改

2020/10/28 9:30 LiXianDe
课程管理后台增加视频上传、删除功能，同时加入微服务架构，进行服务注册发现和调用

2020/11/02 20:49 LiXianDe
统计分析模块编写完成

2020/11/04 20:16添加博客功能

步骤：
        
        npm install markdown-it --save  //用于解析md文档（前后台都需要）
        npm install github-markdown-css --save //md样式
         npm install --save mavon-editor mavon-editor/dist/css/index.css
        npm install element-ui
        
        //后台 src/main.js
        import mavonEditor from 'mavon-editor'
        import 'mavon-editor/dist/css/index.css'
        import 'github-markdown-css'
        Vue.use(mavonEditor)
        
        //src/api添加blog.js
        //src/router添加博客路由
        //src/views/blog添加页面
   
完成博客后台接口

2020/11/11

添加课程评论功能

修改博客修改更新问题     