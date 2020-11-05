import request from '@/utils/request'
export default {
  // 1 博客列表（条件查询分页）
  // current当前页 limit每页记录数 queryBlog条件对象
  getBlogListPage(current, limit, queryBlog) {
    return request({
      // url: '/eduservice/blog/pageBlogCondition/'+current+"/"+limit,
      url: '/edublog/blog/pageBlogCondition/'+current+"/"+limit,
      //url: '/edublog/blog/findAll',
      method: 'post',
      // queryBlog条件对象，后端使用RequestBody获取数据
      // data表示把对象转换json进行传递到接口里面
      data: queryBlog
    })
  },
  detail(id){
    return request({
      url:'/edublog/blog/detail/'+id,
      method: 'get',
    })
  },
  // 删除博客
  deleteBlogById(id) {
    return request({
      url: `/edublog/blog/delete/${id}`,
      method: 'delete'
    })
  },

  // 修改博客
  updateBlogInfo(blog) {
    return request({
      url: `/edublog/blog/updateBlog`,
      method: 'post',
      data: blog
    })
  },
  //根据id获取博客信息
  fetchBlogInfoById(id){
    return request({
      url: `/edublog/blog/detail/${id}`,
      method: 'get'
    })
  }
}
