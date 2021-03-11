package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 货柜管理对象 cabinet
 *
 * @author lwt
 * @date 2021-01-22
 */
public class Cabinet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 柜子id */
    private Long cabinetId;

    /** 柜子编号 */
    @Excel(name = "柜子编号")
    private String cabinetBianha;

    /** 投放点id */
    @Excel(name = "投放点id")
    private Long putinId;

    /** 父柜子id */
    @Excel(name = "父柜子id")
    private Long fatherCabinetid;

    /** 祖级列表 */
    private String ancestors;

    /** 显示顺序 */
    private Integer orderNum;

    /** 拥有者 */
    @Excel(name = "拥有者")
    private String user;

    /** 最近一次打开时间 */
    private Date openTime;

    /** 小区id */
    @Excel(name = "小区id")
    private Long plotId;

    /** 密钥 */
    @Excel(name = "密钥")
    private String secret;

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /** 投放点信息 */
    private List<Putindian> putindianList;

    public void setCabinetId(Long cabinetId)
    {
        this.cabinetId = cabinetId;
    }

    public Long getCabinetId()
    {
        return cabinetId;
    }
    public void setCabinetBianha(String cabinetBianha)
    {
        this.cabinetBianha = cabinetBianha;
    }

    public String getCabinetBianha()
    {
        return cabinetBianha;
    }
    public void setPutinId(Long putinId)
    {
        this.putinId = putinId;
    }

    public Long getPutinId()
    {
        return putinId;
    }
    public void setFatherCabinetid(Long fatherCabinetid)
    {
        this.fatherCabinetid = fatherCabinetid;
    }

    public Long getFatherCabinetid()
    {
        return fatherCabinetid;
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
    public void setUser(String user)
    {
        this.user = user;
    }

    public String getUser()
    {
        return user;
    }
    public void setOpenTime(Date openTime)
    {
        this.openTime = openTime;
    }

    public Date getOpenTime()
    {
        return openTime;
    }

    public List<Putindian> getPutindianList()
    {
        return putindianList;
    }

    public void setPutindianList(List<Putindian> putindianList)
    {
        this.putindianList = putindianList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("cabinetId", getCabinetId())
                .append("cabinetBianha", getCabinetBianha())
                .append("putinId", getPutinId())
                .append("fatherCabinetid", getFatherCabinetid())
                .append("ancestors", getAncestors())
                .append("orderNum", getOrderNum())
                .append("user", getUser())
                .append("createTime", getCreateTime())
                .append("openTime", getOpenTime())
                .append("putindianList", getPutindianList())
                .toString();
    }
}
