package com.ruoyi.graduation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 教师意见对象 teacher_suggestion
 * 
 * @author xu
 * @date 2021-06-03
 */
public class TeacherSuggestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师答辩意见ID */
    private Long id;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 建议 */
    @Excel(name = "建议")
    private String suggestion;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setSuggestion(String suggestion) 
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion() 
    {
        return suggestion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherId", getTeacherId())
            .append("studentId", getStudentId())
            .append("taskId", getTaskId())
            .append("suggestion", getSuggestion())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
