package com.ruoyi.graduation.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.StudentTaskMapper;
import com.ruoyi.graduation.domain.StudentTask;
import com.ruoyi.graduation.service.IStudentTaskService;

/**
 * 所有任务Service业务层处理
 * 
 * @author xu
 * @date 2021-06-01
 */
@Service
public class StudentTaskServiceImpl implements IStudentTaskService 
{
    @Autowired
    private StudentTaskMapper studentTaskMapper;

    /**
     * 查询所有任务
     * 
     * @param id 所有任务ID
     * @return 所有任务
     */
    @Override
    public StudentTask selectStudentTaskById(Long id)
    {
        return studentTaskMapper.selectStudentTaskById(id);
    }

    /**
     * 查询所有任务列表
     * 
     * @param studentTask 所有任务
     * @return 所有任务
     */
    @Override
    public List<StudentTask> selectStudentTaskList(StudentTask studentTask)
    {
        return studentTaskMapper.selectStudentTaskList(studentTask);
    }


    /**
     * 新增所有任务
     * 
     * @param studentTask 所有任务
     * @return 结果
     */
    @Override
    public int insertStudentTask(StudentTask studentTask)
    {
        return studentTaskMapper.insertStudentTask(studentTask);
    }

    /**
     * 修改所有任务
     * 
     * @param studentTask 所有任务
     * @return 结果
     */
    @Override
    public int updateStudentTask(StudentTask studentTask)
    {
        studentTask.setUpdateTime(DateUtils.getNowDate());
        return studentTaskMapper.updateStudentTask(studentTask);
    }

    /**
     * 批量删除所有任务
     * 
     * @param ids 需要删除的所有任务ID
     * @return 结果
     */
    @Override
    public int deleteStudentTaskByIds(Long[] ids)
    {
        return studentTaskMapper.deleteStudentTaskByIds(ids);
    }

    /**
     * 删除所有任务信息
     * 
     * @param id 所有任务ID
     * @return 结果
     */
    @Override
    public int deleteStudentTaskById(Long id)
    {
        return studentTaskMapper.deleteStudentTaskById(id);
    }
}
