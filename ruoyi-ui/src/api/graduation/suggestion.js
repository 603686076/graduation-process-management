import request from '@/utils/request'

// 查询教师意见列表
export function listSuggestion(query) {
  return request({
    url: '/graduation/suggestion/list',
    method: 'get',
    params: query
  })
}

// 查询教师意见详细
export function getSuggestion(id) {
  return request({
    url: '/graduation/suggestion/' + id,
    method: 'get'
  })
}

// 新增教师意见
export function addSuggestion(data) {
  return request({
    url: '/graduation/suggestion',
    method: 'post',
    data: data
  })
}

// 修改教师意见
export function updateSuggestion(data) {
  return request({
    url: '/graduation/suggestion',
    method: 'put',
    data: data
  })
}

// 删除教师意见
export function delSuggestion(id) {
  return request({
    url: '/graduation/suggestion/' + id,
    method: 'delete'
  })
}
