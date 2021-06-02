package com.ruoyi.graduation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.StudentFileInfoMapper;
import com.ruoyi.graduation.domain.StudentFileInfo;
import com.ruoyi.graduation.service.IStudentFileInfoService;

/**
 * 学生文件信息Service业务层处理
 * 
 * @author xu
 * @date 2021-06-02
 */
@Service
public class StudentFileInfoServiceImpl implements IStudentFileInfoService 
{
    @Autowired
    private StudentFileInfoMapper studentFileInfoMapper;

    /**
     * 查询学生文件信息
     * 
     * @param fileId 学生文件信息ID
     * @return 学生文件信息
     */
    @Override
    public StudentFileInfo selectStudentFileInfoById(Long fileId)
    {
        return studentFileInfoMapper.selectStudentFileInfoById(fileId);
    }

    @Override
    public StudentFileInfo selectStudentFileInfoByFileName(String fileName) {
        return studentFileInfoMapper.selectStudentFileInfoByFileName(fileName);
    }

    /**
     * 查询学生文件信息列表
     * 
     * @param studentFileInfo 学生文件信息
     * @return 学生文件信息
     */
    @Override
    public List<StudentFileInfo> selectStudentFileInfoList(StudentFileInfo studentFileInfo)
    {
        return studentFileInfoMapper.selectStudentFileInfoList(studentFileInfo);
    }

    /**
     * 新增学生文件信息
     * 
     * @param studentFileInfo 学生文件信息
     * @return 结果
     */
    @Override
    public int insertStudentFileInfo(StudentFileInfo studentFileInfo)
    {
        return studentFileInfoMapper.insertStudentFileInfo(studentFileInfo);
    }

    /**
     * 修改学生文件信息
     * 
     * @param studentFileInfo 学生文件信息
     * @return 结果
     */
    @Override
    public int updateStudentFileInfo(StudentFileInfo studentFileInfo)
    {
        return studentFileInfoMapper.updateStudentFileInfo(studentFileInfo);
    }

    /**
     * 批量删除学生文件信息
     * 
     * @param fileIds 需要删除的学生文件信息ID
     * @return 结果
     */
    @Override
    public int deleteStudentFileInfoByIds(Long[] fileIds)
    {
        return studentFileInfoMapper.deleteStudentFileInfoByIds(fileIds);
    }

    /**
     * 删除学生文件信息信息
     * 
     * @param fileId 学生文件信息ID
     * @return 结果
     */
    @Override
    public int deleteStudentFileInfoById(Long fileId)
    {
        return studentFileInfoMapper.deleteStudentFileInfoById(fileId);
    }
}
