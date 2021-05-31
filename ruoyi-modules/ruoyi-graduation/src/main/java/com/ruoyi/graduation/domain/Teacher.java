package com.ruoyi.graduation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 选择导师任务对象 teacher
 * 
 * @author xu
 * @date 2021-05-30
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 小组 */
    private Long group;

    /** 职称 */
    private String title;

    /** 指导学生数 */
    @Excel(name = "指导学生数")
    private Long quantity;

    /** 描述 */
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGroup(Long group) 
    {
        this.group = group;
    }

    public Long getGroup() 
    {
        return group;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("group", getGroup())
            .append("title", getTitle())
            .append("quantity", getQuantity())
            .append("description", getDescription())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
