package com.ruoyi.graduation.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.graduation.mapper.TeacherSuggestionMapper;
import com.ruoyi.graduation.domain.TeacherSuggestion;
import com.ruoyi.graduation.service.ITeacherSuggestionService;

/**
 * 教师意见Service业务层处理
 * 
 * @author xu
 * @date 2021-06-03
 */
@Service
public class TeacherSuggestionServiceImpl implements ITeacherSuggestionService 
{
    @Autowired
    private TeacherSuggestionMapper teacherSuggestionMapper;

    /**
     * 查询教师意见
     * 
     * @param id 教师意见ID
     * @return 教师意见
     */
    @Override
    public TeacherSuggestion selectTeacherSuggestionById(Long id)
    {
        return teacherSuggestionMapper.selectTeacherSuggestionById(id);
    }

    /**
     * 查询教师意见列表
     * 
     * @param teacherSuggestion 教师意见
     * @return 教师意见
     */
    @Override
    public List<TeacherSuggestion> selectTeacherSuggestionList(TeacherSuggestion teacherSuggestion)
    {
        return teacherSuggestionMapper.selectTeacherSuggestionList(teacherSuggestion);
    }

    /**
     * 新增教师意见
     * 
     * @param teacherSuggestion 教师意见
     * @return 结果
     */
    @Override
    public int insertTeacherSuggestion(TeacherSuggestion teacherSuggestion)
    {
        return teacherSuggestionMapper.insertTeacherSuggestion(teacherSuggestion);
    }

    /**
     * 修改教师意见
     * 
     * @param teacherSuggestion 教师意见
     * @return 结果
     */
    @Override
    public int updateTeacherSuggestion(TeacherSuggestion teacherSuggestion)
    {
        teacherSuggestion.setUpdateTime(DateUtils.getNowDate());
        return teacherSuggestionMapper.updateTeacherSuggestion(teacherSuggestion);
    }

    /**
     * 批量删除教师意见
     * 
     * @param ids 需要删除的教师意见ID
     * @return 结果
     */
    @Override
    public int deleteTeacherSuggestionByIds(Long[] ids)
    {
        return teacherSuggestionMapper.deleteTeacherSuggestionByIds(ids);
    }

    /**
     * 删除教师意见信息
     * 
     * @param id 教师意见ID
     * @return 结果
     */
    @Override
    public int deleteTeacherSuggestionById(Long id)
    {
        return teacherSuggestionMapper.deleteTeacherSuggestionById(id);
    }
}
