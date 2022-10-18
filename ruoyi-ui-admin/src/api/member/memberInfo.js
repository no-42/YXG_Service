import request from '@/utils/request'

// 查询会员信息列表
export function listMemberInfo(query) {
  return request({
    url: '/member/memberInfo/list',
    method: 'get',
    params: query
  })
}

// 查询会员信息详细
export function getMemberInfo(id) {
  return request({
    url: '/member/memberInfo/' + id,
    method: 'get'
  })
}

// 新增会员信息
export function addMemberInfo(data) {
  return request({
    url: '/member/memberInfo',
    method: 'post',
    data: data
  })
}

// 修改会员信息
export function updateMemberInfo(data) {
  return request({
    url: '/member/memberInfo',
    method: 'put',
    data: data
  })
}

// 删除会员信息
export function delMemberInfo(id) {
  return request({
    url: '/member/memberInfo/' + id,
    method: 'delete'
  })
}
