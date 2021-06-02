package com.ruoyi.graduation.service;

import java.util.List;
import com.ruoyi.graduation.domain.StudentTask;

/**
 * 所有任务Service接口
 * 
 * @author xu
 * @date 2021-06-01
 */
public interface IStudentTaskService 
{
    /**
     * 查询所有任务
     * 
     * @param id 所有任务ID
     * @return 所有任务
     */
    public StudentTask selectStudentTaskById(Long id);

    /**
     * 查询所有任务列表
     * 
     * @param studentTask 所有任务
     * @return 所有任务集合
     */
    public List<StudentTask> selectStudentTaskList(StudentTask studentTask);

    /**
     * 新增所有任务
     * 
     * @param studentTask 所有任务
     * @return 结果
     */
    public int insertStudentTask(StudentTask studentTask);

    /**
     * 修改所有任务
     * 
     * @param studentTask 所有任务
     * @return 结果
     */
    public int updateStudentTask(StudentTask studentTask);

    /**
     * 批量删除所有任务
     * 
     * @param ids 需要删除的所有任务ID
     * @return 结果
     */
    public int deleteStudentTaskByIds(Long[] ids);

    /**
     * 删除所有任务信息
     * 
     * @param id 所有任务ID
     * @return 结果
     */
    public int deleteStudentTaskById(Long id);
}
