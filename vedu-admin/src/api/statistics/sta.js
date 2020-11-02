import request from '@/utils/request'

export default {
  createStatistics(day) {
    return request({
      url: `/staservice/statistics-daily/${day}`,
      method: 'post'
    })
  },
  showChart(searchObj) {
    return request({
      url: `/staservice/statistics-daily/getEchartsDate/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
      method: 'get'
    })
  }
}
