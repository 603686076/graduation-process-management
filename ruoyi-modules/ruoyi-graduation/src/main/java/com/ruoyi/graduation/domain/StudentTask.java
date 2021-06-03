package com.ruoyi.graduation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 所有任务对象 student_task
 * 
 * @author xu
 * @date 2021-06-01
 */
public class StudentTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生任务ID */
    private Long id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String description;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String filename;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("description", getDescription())
            .append("filename", getFilename())
            .append("updateTime", getUpdateTime())
            .append("studentId", getStudentId())
            .toString();
    }
}
