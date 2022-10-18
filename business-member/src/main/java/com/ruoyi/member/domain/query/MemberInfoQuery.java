package com.ruoyi.member.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 会员信息查询条件
 *
 * @author thetbw
 * @date 2022-10-18
 */
@Getter
@Setter
@ToString
public class MemberInfoQuery extends BaseQuery {

    
    
    /**
     *  用户名称
     */
    @QueryField(type = QueryField.CompareType.LIKE)
    private String name;
    
    
    /**
     *  用户手机号
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String phone;
    
    
    /**
     *  用户头像url
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String avatar;
    
    
    
}
