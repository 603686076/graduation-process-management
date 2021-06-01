import request from '@/utils/request'

// 查询选择导师任务列表
export function listTeacher(query) {
  return request({
    url: '/graduation/choose/list',
    method: 'get',
    params: query
  })
}

// 查询选择导师任务详细
export function getTeacher(id) {
  return request({
    url: '/graduation/choose/' + id,
    method: 'get'
  })
}

// 新增选择导师任务
export function addTeacher(data) {
  return request({
    url: '/graduation/choose',
    method: 'post',
    data: data
  })
}

// 修改选择导师任务
export function updateTeacher(data) {
  return request({
    url: '/graduation/choose',
    method: 'put',
    data: data
  })
}

// 删除选择导师任务
export function delTeacher(id) {
  return request({
    url: '/graduation/choose/' + id,
    method: 'delete'
  })
}
