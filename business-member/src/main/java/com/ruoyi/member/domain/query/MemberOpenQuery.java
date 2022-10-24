package com.ruoyi.member.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 第三方会员查询条件
 *
 * @author thetbw
 * @date 2022-10-21
 */
@Getter
@Setter
@ToString
public class MemberOpenQuery extends BaseQuery{
    
    /**
     *  所属会员id
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String memberId;


    /**
     *  第三方平台类型
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String openType;


    /**
     *  第三方平台的用户id
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String openId;
    
    

}
