package com.ruoyi.graduation.service;

import java.util.List;
import com.ruoyi.graduation.domain.Task;

/**
 * 任务Service接口
 * 
 * @author xu
 * @date 2021-05-05
 */
public interface ITaskService 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    public Task selectTaskById(Long id);

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    public int deleteTaskById(Long id);

    List<Task> selectStudentTaskList();
}
