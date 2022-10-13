import request from '@/utils/request'

// 查询登录日志列表
export function list(query) {
  return request({
    url: '/monitor/logininfo/list',
    method: 'get',
    params: query
  })
}

// 删除登录日志
export function delLoginInfo(infoId) {
  return request({
    url: '/monitor/logininfo/' + infoId,
    method: 'delete'
  })
}

// 解锁用户登录状态
export function unlockLoginInfo(userName) {
  return request({
    url: '/monitor/logininfo/unlock/' + userName,
    method: 'get'
  })
}

// 清空登录日志
export function cleanLoginInfo() {
  return request({
    url: '/monitor/logininfo/clean',
    method: 'delete'
  })
}
