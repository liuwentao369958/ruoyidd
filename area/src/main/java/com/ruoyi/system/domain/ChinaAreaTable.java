package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 地区对象 china_area_table
 * 
 * @author puzzle
 * @date 2021-01-12
 */
public class ChinaAreaTable extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区id */
    private Long areaId;

    /** 父地区id */
    @Excel(name = "父地区id")
    private Long parentareaId;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String areaName;

    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }


    public Long getAreaId() 
    {
        return areaId;
    }
    public void setParentareaId(Long parentareaId) 
    {
        this.parentareaId = parentareaId;
    }

    public Long getParentareaId() 
    {
        return parentareaId;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaId", getAreaId())
            .append("parentareaId", getParentareaId())
            .append("ancestors", getAncestors())
            .append("areaName", getAreaName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
