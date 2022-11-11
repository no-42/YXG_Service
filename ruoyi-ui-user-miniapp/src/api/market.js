import request from '../utils/request'

/**
 * 获取所有分类
 */
function getAllCategory() {
    return request("/market/category", "GET", null)
}

/**
 * 获取所有规格
 */
function getAllSpec() {
    return request("/market/spec", "GET", null)
}

/**
 * 获取所有产地
 */
function getAllOrigin() {
    return request("/market/origin", "GET", null)
}

/**
 * 获取所有供应商
 */
function getAllSupplier() {
    return request("/market/supplier", "GET", null)
}

/**
 * 获取商品列表
 * @param query 查询条件
 * @param pageNum 当前页
 * @param pageSize 页大小
 */
function getGoodsList(query, pageNum, pageSize) {
    query ||= {}
    query.pageNum = pageNum || 1
    query.pageSize = pageSize || 50
    return request("/market/goods", "GET", query)
}

export {getAllCategory, getAllSupplier, getGoodsList, getAllSpec, getAllOrigin}