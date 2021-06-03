import request from '@/utils/request'

// 查询学生文件信息列表
export function listStudentfileinfo(query) {
  return request({
    url: '/graduation/studentfileinfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生文件信息详细
export function getStudentfileinfo(fileId) {
  return request({
    url: '/graduation/studentfileinfo/' + fileId,
    method: 'get'
  })
}

// 通过fileName获取学生文件信息详细信息
export function getStudentfileinfoByFileName(fileName) {
  return request({
    url: '/graduation/studentfileinfo/' + fileName,
    method: 'get'
  })
}

// 新增学生文件信息
export function addStudentfileinfo(data) {
  return request({
    url: '/graduation/studentfileinfo',
    method: 'post',
    data: data
  })
}

// 修改学生文件信息
export function updateStudentfileinfo(data) {
  return request({
    url: '/graduation/studentfileinfo',
    method: 'put',
    data: data
  })
}

// 删除学生文件信息
export function delStudentfileinfo(fileId) {
  return request({
    url: '/graduation/studentfileinfo/' + fileId,
    method: 'delete'
  })
}
