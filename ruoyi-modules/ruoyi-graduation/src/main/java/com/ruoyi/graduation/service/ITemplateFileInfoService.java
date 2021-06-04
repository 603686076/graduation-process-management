package com.ruoyi.graduation.service;

import java.util.List;
import com.ruoyi.graduation.domain.TemplateFileInfo;

/**
 * 模板文件信息Service接口
 * 
 * @author xu
 * @date 2021-06-04
 */
public interface ITemplateFileInfoService 
{
    /**
     * 查询模板文件信息
     * 
     * @param fileId 模板文件信息ID
     * @return 模板文件信息
     */
    public TemplateFileInfo selectTemplateFileInfoById(Long fileId);

    /**
     * 查询模板文件信息列表
     * 
     * @param templateFileInfo 模板文件信息
     * @return 模板文件信息集合
     */
    public List<TemplateFileInfo> selectTemplateFileInfoList(TemplateFileInfo templateFileInfo);

    /**
     * 新增模板文件信息
     * 
     * @param templateFileInfo 模板文件信息
     * @return 结果
     */
    public int insertTemplateFileInfo(TemplateFileInfo templateFileInfo);

    /**
     * 修改模板文件信息
     * 
     * @param templateFileInfo 模板文件信息
     * @return 结果
     */
    public int updateTemplateFileInfo(TemplateFileInfo templateFileInfo);

    /**
     * 批量删除模板文件信息
     * 
     * @param fileIds 需要删除的模板文件信息ID
     * @return 结果
     */
    public int deleteTemplateFileInfoByIds(Long[] fileIds);

    /**
     * 删除模板文件信息信息
     * 
     * @param fileId 模板文件信息ID
     * @return 结果
     */
    public int deleteTemplateFileInfoById(Long fileId);
}
