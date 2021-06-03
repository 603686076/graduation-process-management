import request from '@/utils/request'

// 查询所有任务列表
export function listStudenttaskView(query) {
  return request({
    url: '/graduation/viewsuggestions/list',
    method: 'get',
    params: query
  })
}