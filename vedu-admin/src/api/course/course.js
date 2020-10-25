import request from '@/utils/request'

export default {
  removeCourseById(courseId){
    return request({
      url: `/eduservice/course/removeCourseById/${courseId}`,
      method: 'delete'
    })
  },
  getCoursePageList(current, limit, courseQuery) {
    return request({
      url: `/eduservice/course/pageCourseCondition/${current}/${limit}`,
      method: 'post',
      data: courseQuery
    })
  },
  getAllCourseInfo(){
    return request({
      url: `/eduservice/course/getAllCourseInfo`,
      method: 'get'
    })
  },
  saveCourseInfo(courseInfo) {
    return request({
      url: `/eduservice/course/saveCourseInfo`,
      method: 'post',
      data: courseInfo
    })
  },
  getCourseInfoById(courseId) {
    return request({
      url: `/eduservice/course/courseInfo/${courseId}`,
      method: 'get'
    })
  },
  updateCourseInfoById(courseInfo) {
    return request({
      url: `/eduservice/course/updateCourseInfo`,
      method: 'put',
      data: courseInfo
    })
  },
  getCoursePublishInfoById(id) {
    return request({
      url: `/eduservice/course/getCoursePublishForm/${id}`,
      method: 'get'
    })
  },
  publishCourse(id) {
    return request({
      url: `/eduservice/course/publishCourseById/${id}`,
      method: 'put'
    })
  }
}
