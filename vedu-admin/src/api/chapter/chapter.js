import request from '@/utils/request'


export default {

  getNestedTreeList(courseId) {
    return request({
      url: `/eduservice/eduChapter/nestList/${courseId}`,
      method: 'get'
    })
  },
  save(chapter) {
    return request({
      url: `/eduservice/eduChapter/addChapter`,
      method: 'post',
      data: chapter
    })
  },
  removeById(id) {
    return request({
      url: `/eduservice/eduChapter/deleteById/${id}`,
      method: 'delete'
    })
  },
  updateById(chapter) {
    return request({
      url: `/eduservice/eduChapter/updateById`,
      method: 'put',
      data: chapter
    })
  },
  gteById(id) {
    return request({
      url: `/eduservice/eduChapter/getById/${id}`,
      method: 'get'
    })
  },
}
