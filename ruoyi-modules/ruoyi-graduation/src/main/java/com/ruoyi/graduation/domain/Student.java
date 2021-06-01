package com.ruoyi.graduation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 学生对象 student
 * 
 * @author xu
 * @date 2021-06-01
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 班级 */
    @Excel(name = "班级")
    private String clazz;

    /** 导师ID */
    @Excel(name = "导师ID")
    private Long teacherId;

    /** 小组 */
    @Excel(name = "小组")
    private Long group;

    /** 课设题目 */
    @Excel(name = "课设题目")
    private String topic;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClazz(String clazz) 
    {
        this.clazz = clazz;
    }

    public String getClazz() 
    {
        return clazz;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setGroup(Long group) 
    {
        this.group = group;
    }

    public Long getGroup() 
    {
        return group;
    }
    public void setTopic(String topic) 
    {
        this.topic = topic;
    }

    public String getTopic() 
    {
        return topic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clazz", getClazz())
            .append("teacherId", getTeacherId())
            .append("group", getGroup())
            .append("topic", getTopic())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
