package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 小区管理对象 community_management
 *
 * @author ruoyi
 * @date 2021-01-22
 */
public class CommunityManagement extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 小区id */
    private Long plotId;

    /** 父小区id */
    @Excel(name = "父小区id")
    private Long parentplotId;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String plotName;

    public void setPlotId(Long plotId)
    {
        this.plotId = plotId;
    }

    public Long getPlotId()
    {
        return plotId;
    }
    public void setParentplotId(Long parentplotId)
    {
        this.parentplotId = parentplotId;
    }

    public Long getParentplotId()
    {
        return parentplotId;
    }
    public void setPlotName(String plotName)
    {
        this.plotName = plotName;
    }

    public String getPlotName()
    {
        return plotName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("plotId", getPlotId())
                .append("parentplotId", getParentplotId())
                .append("ancestors", getAncestors())
                .append("plotName", getPlotName())
                .append("orderNum", getOrderNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}