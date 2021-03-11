package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.crypto.SecretKey;

/**
 * 投放点对象 putindian
 * 
 * @author lwt
 * @date 2021-01-22
 */
public class Putindian extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投放点id */
    @Excel(name = "投放点id")
    private Long putinId;


    /** 投放点名字 */
    @Excel(name = "投放点名字")
    private String putinName;

    /** 小区id */
    @Excel(name = "小区id")
    private Long plotId;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 密钥 */
    @Excel(name = "密钥")
    private String secret;

    public String getPutinName() {
        return putinName;
    }

    public void setPutinName(String putinName) {
        this.putinName = putinName;
    }

    public void setPutinId(Long putinId)
    {
        this.putinId = putinId;
    }

    public Long getPutinId() 
    {
        return putinId;
    }
    public void setPlotId(Long plotId) 
    {
        this.plotId = plotId;
    }

    public Long getPlotId() 
    {
        return plotId;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getSecret()
    {
        return secret;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("putinId", getPutinId())
            .append("plotId", getPlotId())
            .append("ancestors", getAncestors())
            .append("orderNum", getOrderNum())
            .append("createTime", getCreateTime())
            .append("secret", getSecret())
            .toString();
    }
}
