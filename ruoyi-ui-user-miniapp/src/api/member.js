import request from '../utils/request'


// 更新会员信息
function updateMemberInfo(member) {
    return request("/member", "PUT", member)
}


export {updateMemberInfo}