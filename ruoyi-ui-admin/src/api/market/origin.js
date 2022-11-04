import request from '@/utils/request'

// 查询商品产地列表
export function listOrigin(query) {
  return request({
    url: '/market/origin/list',
    method: 'get',
    params: query
  })
}

// 查询商品产地详细
export function getOrigin(id) {
  return request({
    url: '/market/origin/' + id,
    method: 'get'
  })
}

// 新增商品产地
export function addOrigin(data) {
  return request({
    url: '/market/origin',
    method: 'post',
    data: data
  })
}

// 修改商品产地
export function updateOrigin(data) {
  return request({
    url: '/market/origin',
    method: 'put',
    data: data
  })
}

// 删除商品产地
export function delOrigin(id) {
  return request({
    url: '/market/origin/' + id,
    method: 'delete'
  })
}
