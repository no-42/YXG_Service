import request from '@/utils/request'

// 查询商品供应商列表
export function listSupplier(query) {
  return request({
    url: '/market/supplier/list',
    method: 'get',
    params: query
  })
}

// 查询商品供应商详细
export function getSupplier(id) {
  return request({
    url: '/market/supplier/' + id,
    method: 'get'
  })
}

// 新增商品供应商
export function addSupplier(data) {
  return request({
    url: '/market/supplier',
    method: 'post',
    data: data
  })
}

// 修改商品供应商
export function updateSupplier(data) {
  return request({
    url: '/market/supplier',
    method: 'put',
    data: data
  })
}

// 删除商品供应商
export function delSupplier(id) {
  return request({
    url: '/market/supplier/' + id,
    method: 'delete'
  })
}
