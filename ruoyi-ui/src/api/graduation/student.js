import request from '@/utils/request'

// 查询教师学生关联列表
export function listStudent(query) {
  return request({
    url: '/graduation/student/list',
    method: 'get',
    params: query
  })
}

// 查询教师学生关联详细
export function getStudent(id) {
  return request({
    url: '/graduation/student/' + id,
    method: 'get'
  })
}

// 新增教师学生关联
export function addStudent(data) {
  return request({
    url: '/graduation/student',
    method: 'post',
    data: data
  })
}

// 修改教师学生关联
export function updateStudent(data) {
  return request({
    url: '/graduation/student',
    method: 'put',
    data: data
  })
}

// 删除教师学生关联
export function delStudent(id) {
  return request({
    url: '/graduation/student/' + id,
    method: 'delete'
  })
}
