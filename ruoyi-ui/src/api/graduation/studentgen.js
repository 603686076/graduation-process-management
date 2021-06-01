import request from '@/utils/request'

// 查询学生列表
export function listStudentgen(query) {
  return request({
    url: '/graduation/studentgen/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudentgen(id) {
  return request({
    url: '/graduation/studentgen/' + id,
    method: 'get'
  })
}

// 新增学生
export function addStudentgen(data) {
  return request({
    url: '/graduation/studentgen',
    method: 'post',
    data: data
  })
}

// 修改学生
export function updateStudentgen(data) {
  return request({
    url: '/graduation/studentgen',
    method: 'put',
    data: data
  })
}

// 删除学生
export function delStudentgen(id) {
  return request({
    url: '/graduation/studentgen/' + id,
    method: 'delete'
  })
}
