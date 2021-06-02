package com.ruoyi.graduation.service;

import java.util.List;
import com.ruoyi.graduation.domain.StudentFileInfo;

/**
 * 学生文件信息Service接口
 * 
 * @author xu
 * @date 2021-06-02
 */
public interface IStudentFileInfoService 
{
    /**
     * 查询学生文件信息
     * 
     * @param fileId 学生文件信息ID
     * @return 学生文件信息
     */
    public StudentFileInfo selectStudentFileInfoById(Long fileId);

    /**
     * 查询学生文件信息
     *
     * @param fileName 学生文件信息 fileName
     * @return 学生文件信息
     */
    public StudentFileInfo selectStudentFileInfoByFileName(String fileName);

    /**
     * 查询学生文件信息列表
     * 
     * @param studentFileInfo 学生文件信息
     * @return 学生文件信息集合
     */
    public List<StudentFileInfo> selectStudentFileInfoList(StudentFileInfo studentFileInfo);

    /**
     * 新增学生文件信息
     * 
     * @param studentFileInfo 学生文件信息
     * @return 结果
     */
    public int insertStudentFileInfo(StudentFileInfo studentFileInfo);

    /**
     * 修改学生文件信息
     * 
     * @param studentFileInfo 学生文件信息
     * @return 结果
     */
    public int updateStudentFileInfo(StudentFileInfo studentFileInfo);

    /**
     * 批量删除学生文件信息
     * 
     * @param fileIds 需要删除的学生文件信息ID
     * @return 结果
     */
    public int deleteStudentFileInfoByIds(Long[] fileIds);

    /**
     * 删除学生文件信息信息
     * 
     * @param fileId 学生文件信息ID
     * @return 结果
     */
    public int deleteStudentFileInfoById(Long fileId);
}
