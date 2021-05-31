package com.ruoyi.graduation.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.graduation.domain.Teacher;
import com.ruoyi.graduation.mapper.ChooseTeacherMapper;
import com.ruoyi.graduation.service.IChooseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选择导师任务Service业务层处理
 * 
 * @author xu
 * @date 2021-05-30
 */
@Service
public class ChooseTeacherServiceImpl implements IChooseTeacherService
{
    @Autowired
    private ChooseTeacherMapper teacherMapper;

    /**
     * 查询选择导师任务
     * 
     * @param id 选择导师任务ID
     * @return 选择导师任务
     */
    @Override
    public Teacher selectTeacherById(Long id)
    {
        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 查询选择导师任务列表
     * 
     * @param String 选择导师任务
     * @return 选择导师任务
     */
    @Override
    public List<Teacher> selectTeacherList(String nickName)
    {
        return teacherMapper.selectTeacherList(nickName);
    }

    /**
     * 新增选择导师任务
     * 
     * @param teacher 选择导师任务
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher)
    {
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改选择导师任务
     * 
     * @param teacher 选择导师任务
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        teacher.setUpdateTime(DateUtils.getNowDate());
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除选择导师任务
     * 
     * @param ids 需要删除的选择导师任务ID
     * @return 结果
     */
    @Override
    public int deleteTeacherByIds(Long[] ids)
    {
        return teacherMapper.deleteTeacherByIds(ids);
    }

    /**
     * 删除选择导师任务信息
     * 
     * @param id 选择导师任务ID
     * @return 结果
     */
    @Override
    public int deleteTeacherById(Long id)
    {
        return teacherMapper.deleteTeacherById(id);
    }
}
