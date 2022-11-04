import request from '../utils/request'


function loadDict(type) {
    return request("/common/dict" + type, "GET", null)
}

function loadConfig(key) {
    return request("/common/")
}


export {loadDict, loadConfig}