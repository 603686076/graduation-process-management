package com.ruoyi.graduation.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 任务对象 task
 * 
 * @author xu
 * @date 2021-05-05
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 任务类型（1新闻公告类 2文件 3选导师 4答辩意见） */
    @Excel(name = "任务类型", readConverterExp = "1=新闻公告类,2=文件,3=选导师,4=答辩意见")
    private String type;

    /** 状态（0未开始 1开始 2结束） */
    @Excel(name = "状态", readConverterExp = "0=未开始,1=开始,2=结束")
    private String status;

    /** 完成角色（1学生 2教师） */
    @Excel(name = "完成角色", readConverterExp = "1=学生,2=教师")
    private String target;

    /** 上传文件名称规则 */
    @Excel(name = "上传文件名称规则")
    private String uploadRegexName;

    /** 模板文件名称 */
    @Excel(name = "模板文件名称")
    private String filename;

    /** 发布者ID */
    @Excel(name = "发布者ID")
    private Long teacherId;

    /** 文件类型后缀 */
    @Excel(name = "文件类型后缀")
    private String extName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setTarget(String target) 
    {
        this.target = target;
    }

    public String getTarget() 
    {
        return target;
    }
    public void setUploadRegexName(String uploadRegexName) 
    {
        this.uploadRegexName = uploadRegexName;
    }

    public String getUploadRegexName() 
    {
        return uploadRegexName;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setExtName(String extName) 
    {
        this.extName = extName;
    }

    public String getExtName() 
    {
        return extName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("type", getType())
            .append("status", getStatus())
            .append("target", getTarget())
            .append("uploadRegexName", getUploadRegexName())
            .append("filename", getFilename())
            .append("teacherId", getTeacherId())
            .append("extName", getExtName())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
