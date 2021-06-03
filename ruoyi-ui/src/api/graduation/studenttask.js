import request from '@/utils/request'

// 查询所有任务列表
export function listStudenttask(query) {
  return request({
    url: '/graduation/studenttask/list',
    method: 'get',
    params: query
  })
}

// 查询所有任务详细
export function getStudenttask(id) {
  return request({
    url: '/graduation/studenttask/' + id,
    method: 'get'
  })
}

// 新增所有任务
export function addStudenttask(data) {
  return request({
    url: '/graduation/studenttask',
    method: 'post',
    data: data
  })
}

// 修改所有任务
export function updateStudenttask(data) {
  return request({
    url: '/graduation/studenttask',
    method: 'put',
    data: data
  })
}

// 删除所有任务
export function delStudenttask(id) {
  return request({
    url: '/graduation/studenttask/' + id,
    method: 'delete'
  })
}
