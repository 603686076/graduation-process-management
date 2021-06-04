import request from '@/utils/request'


// 查询所有选择过这个老师的任务的文件列表
export function listChosen(query) {
  return request({
    url: '/graduation/studentpapers/listchosen',
    method: 'get',
    params: query
  })
}

// 查询所有任务列表
export function listStudenttask(query) {
  return request({
    url: '/graduation/studentpapers/list',
    method: 'get',
    params: query
  })
}

// 查询所有任务详细
export function getStudenttask(id) {
  return request({
    url: '/graduation/studentpapers/' + id,
    method: 'get'
  })
}

// 新增所有任务
export function addStudenttask(data) {
  return request({
    url: '/graduation/studentpapers',
    method: 'post',
    data: data
  })
}

// 修改所有任务
export function updateStudenttask(data) {
  return request({
    url: '/graduation/studentpapers',
    method: 'put',
    data: data
  })
}

// 删除所有任务
export function delStudenttask(id) {
  return request({
    url: '/graduation/studentpapers/' + id,
    method: 'delete'
  })
}
