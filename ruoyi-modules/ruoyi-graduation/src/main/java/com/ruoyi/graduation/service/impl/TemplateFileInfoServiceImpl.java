package com.ruoyi.graduation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.TemplateFileInfoMapper;
import com.ruoyi.graduation.domain.TemplateFileInfo;
import com.ruoyi.graduation.service.ITemplateFileInfoService;

/**
 * 模板文件信息Service业务层处理
 * 
 * @author xu
 * @date 2021-06-04
 */
@Service
public class TemplateFileInfoServiceImpl implements ITemplateFileInfoService 
{
    @Autowired
    private TemplateFileInfoMapper templateFileInfoMapper;

    /**
     * 查询模板文件信息
     * 
     * @param fileId 模板文件信息ID
     * @return 模板文件信息
     */
    @Override
    public TemplateFileInfo selectTemplateFileInfoById(Long fileId)
    {
        return templateFileInfoMapper.selectTemplateFileInfoById(fileId);
    }

    /**
     * 查询模板文件信息列表
     * 
     * @param templateFileInfo 模板文件信息
     * @return 模板文件信息
     */
    @Override
    public List<TemplateFileInfo> selectTemplateFileInfoList(TemplateFileInfo templateFileInfo)
    {
        return templateFileInfoMapper.selectTemplateFileInfoList(templateFileInfo);
    }

    /**
     * 新增模板文件信息
     * 
     * @param templateFileInfo 模板文件信息
     * @return 结果
     */
    @Override
    public int insertTemplateFileInfo(TemplateFileInfo templateFileInfo)
    {
        return templateFileInfoMapper.insertTemplateFileInfo(templateFileInfo);
    }

    /**
     * 修改模板文件信息
     * 
     * @param templateFileInfo 模板文件信息
     * @return 结果
     */
    @Override
    public int updateTemplateFileInfo(TemplateFileInfo templateFileInfo)
    {
        return templateFileInfoMapper.updateTemplateFileInfo(templateFileInfo);
    }

    /**
     * 批量删除模板文件信息
     * 
     * @param fileIds 需要删除的模板文件信息ID
     * @return 结果
     */
    @Override
    public int deleteTemplateFileInfoByIds(Long[] fileIds)
    {
        return templateFileInfoMapper.deleteTemplateFileInfoByIds(fileIds);
    }

    /**
     * 删除模板文件信息信息
     * 
     * @param fileId 模板文件信息ID
     * @return 结果
     */
    @Override
    public int deleteTemplateFileInfoById(Long fileId)
    {
        return templateFileInfoMapper.deleteTemplateFileInfoById(fileId);
    }
}
