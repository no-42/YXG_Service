package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Treeselect树结构实体类
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private String id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;
    
}
