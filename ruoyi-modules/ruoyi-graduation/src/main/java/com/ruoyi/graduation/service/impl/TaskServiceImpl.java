package com.ruoyi.graduation.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.TaskMapper;
import com.ruoyi.graduation.domain.Task;
import com.ruoyi.graduation.service.ITaskService;

/**
 * 任务Service业务层处理
 * 
 * @author xu
 * @date 2021-05-05
 */
@Service
public class TaskServiceImpl implements ITaskService 
{
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        return taskMapper.insertTask(task);
    }

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }
}
