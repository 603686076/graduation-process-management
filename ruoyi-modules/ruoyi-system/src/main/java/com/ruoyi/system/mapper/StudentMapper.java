package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Student;

/**
 * 学生Mapper接口
 * 
 * @author xu
 * @date 2021-06-01
 */
public interface StudentMapper 
{
    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    public Student selectStudentById(Long id);

    /**
     * 查询学生列表
     * 
     * @param student 学生
     * @return 学生集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生
     * 
     * @param student 学生
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 结果
     */
    public int deleteStudentById(Long id);

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudentByIds(Long[] ids);
}
