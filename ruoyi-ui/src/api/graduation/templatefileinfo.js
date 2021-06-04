import request from '@/utils/request'

// 查询模板文件信息列表
export function listTemplatefileinfo(query) {
  return request({
    url: '/graduation/templatefileinfo/list',
    method: 'get',
    params: query
  })
}

// 查询模板文件信息详细
export function getTemplatefileinfo(fileId) {
  return request({
    url: '/graduation/templatefileinfo/' + fileId,
    method: 'get'
  })
}

// 新增模板文件信息
export function addTemplatefileinfo(data) {
  return request({
    url: '/graduation/templatefileinfo',
    method: 'post',
    data: data
  })
}

// 修改模板文件信息
export function updateTemplatefileinfo(data) {
  return request({
    url: '/graduation/templatefileinfo',
    method: 'put',
    data: data
  })
}

// 删除模板文件信息
export function delTemplatefileinfo(fileId) {
  return request({
    url: '/graduation/templatefileinfo/' + fileId,
    method: 'delete'
  })
}
