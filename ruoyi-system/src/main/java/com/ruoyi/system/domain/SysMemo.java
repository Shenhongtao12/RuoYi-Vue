package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 备忘录对象 sys_memo
 * 
 * @author aaron
 * @date 2022-07-23
 */
@TableName("sys_memo")
public class SysMemo extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    @TableId(type = IdType.AUTO)
    private Long noticeId;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @Excel(name = "公告类型", readConverterExp = "1=通知,2=公告")
    private String noticeType;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String noticeContent;

    private Integer inventory;

    /** 公告状态（0正常 1关闭） */
    @Excel(name = "公告状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /**
     * 逻辑删除
     * 插入时填充
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    public void setNoticeId(Long noticeId) 
    {
        this.noticeId = noticeId;
    }

    public Long getNoticeId() 
    {
        return noticeId;
    }
    public void setNoticeTitle(String noticeTitle) 
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle() 
    {
        return noticeTitle;
    }
    public void setNoticeType(String noticeType) 
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType() 
    {
        return noticeType;
    }
    public void setNoticeContent(String noticeContent) 
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent() 
    {
        return noticeContent;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeType", getNoticeType())
            .append("noticeContent", getNoticeContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .toString();
    }
}
