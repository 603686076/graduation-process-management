import request from '@/utils/request'

// 查询学生课题
export function listStudentTopic(query) {
  return request({
    url: '/graduation/determine/list',
    method: 'get',
    params: query
  })
}

// 查询教师学生关联详细
export function selectStudentById(id) {
  return request({
    url: '/graduation/determine/' + id,
    method: 'get'
  })
}

// 更新学生课题
export function updateStudentTopic(data) {
  return request({
    url: '/graduation/determine',
    method: 'put',
    data: data
  })
}