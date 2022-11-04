import request from '@/utils/request'

// 查询商品规格列表
export function listSpec(query) {
  return request({
    url: '/market/spec/list',
    method: 'get',
    params: query
  })
}

// 查询商品规格详细
export function getSpec(id) {
  return request({
    url: '/market/spec/' + id,
    method: 'get'
  })
}

// 新增商品规格
export function addSpec(data) {
  return request({
    url: '/market/spec',
    method: 'post',
    data: data
  })
}

// 修改商品规格
export function updateSpec(data) {
  return request({
    url: '/market/spec',
    method: 'put',
    data: data
  })
}

// 删除商品规格
export function delSpec(id) {
  return request({
    url: '/market/spec/' + id,
    method: 'delete'
  })
}
