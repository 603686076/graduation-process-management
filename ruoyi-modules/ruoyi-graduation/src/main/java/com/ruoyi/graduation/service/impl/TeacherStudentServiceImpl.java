package com.ruoyi.graduation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.TeacherStudentMapper;
import com.ruoyi.graduation.domain.TeacherStudent;
import com.ruoyi.graduation.service.ITeacherStudentService;

/**
 * 教师学生关联Service业务层处理
 * 
 * @author xu
 * @date 2021-05-31
 */
@Service
public class TeacherStudentServiceImpl implements ITeacherStudentService 
{
    @Autowired
    private TeacherStudentMapper teacherStudentMapper;

    /**
     * 查询教师学生关联
     * 
     * @param id 教师学生关联ID
     * @return 教师学生关联
     */
    @Override
    public TeacherStudent selectTeacherStudentById(Long id)
    {
        return teacherStudentMapper.selectTeacherStudentById(id);
    }

    /**
     * 查询教师学生关联列表
     * 
     * @param teacherStudent 教师学生关联
     * @return 教师学生关联
     */
    @Override
    public List<TeacherStudent> selectTeacherStudentList(TeacherStudent teacherStudent)
    {
        return teacherStudentMapper.selectTeacherStudentList(teacherStudent);
    }

    /**
     * 新增教师学生关联
     * 
     * @param teacherStudent 教师学生关联
     * @return 结果
     */
    @Override
    public int insertTeacherStudent(TeacherStudent teacherStudent)
    {
        return teacherStudentMapper.insertTeacherStudent(teacherStudent);
    }

    /**
     * 修改教师学生关联
     * 
     * @param teacherStudent 教师学生关联
     * @return 结果
     */
    @Override
    public int updateTeacherStudent(TeacherStudent teacherStudent)
    {
        return teacherStudentMapper.updateTeacherStudent(teacherStudent);
    }

    /**
     * 批量删除教师学生关联
     * 
     * @param ids 需要删除的教师学生关联ID
     * @return 结果
     */
    @Override
    public int deleteTeacherStudentByIds(Long[] ids)
    {
        return teacherStudentMapper.deleteTeacherStudentByIds(ids);
    }

    /**
     * 删除教师学生关联信息
     * 
     * @param id 教师学生关联ID
     * @return 结果
     */
    @Override
    public int deleteTeacherStudentById(Long id)
    {
        return teacherStudentMapper.deleteTeacherStudentById(id);
    }
}
