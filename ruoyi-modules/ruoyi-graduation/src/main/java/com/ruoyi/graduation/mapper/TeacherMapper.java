package com.ruoyi.graduation.mapper;

import java.util.List;
import com.ruoyi.graduation.domain.Teacher;

/**
 * 选择导师任务Mapper接口
 * 
 * @author xu
 * @date 2021-05-30
 */
public interface TeacherMapper 
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
     * 删除选择导师任务
     * 
     * @param id 选择导师任务ID
     * @return 结果
     */
    public int deleteTeacherById(Long id);

    /**
     * 批量删除选择导师任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTeacherByIds(Long[] ids);
}
