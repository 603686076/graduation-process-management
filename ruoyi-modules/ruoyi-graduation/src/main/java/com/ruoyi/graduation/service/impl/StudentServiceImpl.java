package com.ruoyi.graduation.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.StudentMapper;
import com.ruoyi.graduation.domain.Student;
import com.ruoyi.graduation.service.IStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author xu
 * @date 2021-06-01
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    @Override
    public Student selectStudentById(Long id)
    {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        student.setUpdateTime(DateUtils.getNowDate());
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    @Override
    public int deleteStudentByIds(Long[] ids)
    {
        return studentMapper.deleteStudentByIds(ids);
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生ID
     * @return 结果
     */
    @Override
    public int deleteStudentById(Long id)
    {
        return studentMapper.deleteStudentById(id);
    }
}
