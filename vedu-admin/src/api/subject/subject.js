import request from '@/utils/request'


export default {

  getNestedTreeList() {
    return request({
      url: `/eduservice/subject/nestedList`,
      method: 'get'
    })
  }
}
