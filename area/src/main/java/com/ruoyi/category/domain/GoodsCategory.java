package com.ruoyi.category.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 商品分类对象 goods_category
 * 
 * @author LWT
 * @date 2021-01-13
 */
public class GoodsCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long projectId;

    /** 父商品id */
    @Excel(name = "父商品id")
    private Long parentprojectId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String projectName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String images;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setParentprojectId(Long parentprojectId) 
    {
        this.parentprojectId = parentprojectId;
    }

    public Long getParentprojectId() 
    {
        return parentprojectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("parentprojectId", getParentprojectId())
            .append("ancestors", getAncestors())
            .append("projectName", getProjectName())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("images", getImages())
            .toString();
    }
}
