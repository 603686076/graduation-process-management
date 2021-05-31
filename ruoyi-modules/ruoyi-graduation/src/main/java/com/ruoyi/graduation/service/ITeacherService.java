package com.ruoyi.graduation.service;

import java.util.List;
import com.ruoyi.graduation.domain.Teacher;

/**
 * 选择导师任务Service接口
 * 
 * @author xu
 * @date 2021-05-30
 */
public interface ITeacherService 
{
    /**
     * 查询选择导师任务
     * 
     * @param id 选择导师任务ID
     * @return 选择导师任务
     */
    public Teacher selectTeacherById(Long id);

    /**
     * 查询选择导师任务列表
     * 
     * @param teacher 选择导师任务
     * @return 选择导师任务集合
     */
    public List<Teacher> selectTeacherList(String nickName);

    /**
     * 新增选择导师任务
     * 
     * @param teacher 选择导师任务
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改选择导师任务
     * 
     * @param teacher 选择导师任务
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除选择导师任务
     * 
     * @param ids 需要删除的选择导师任务ID
     * @return 结果
     */
    public int deleteTeacherByIds(Long[] ids);

    /**
     * 删除选择导师任务信息
     * 
     * @param id 选择导师任务ID
     * @return 结果
     */
    public int deleteTeacherById(Long id);
}
