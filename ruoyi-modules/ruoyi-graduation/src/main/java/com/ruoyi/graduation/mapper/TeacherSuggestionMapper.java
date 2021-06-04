package com.ruoyi.graduation.mapper;

import java.util.List;
import com.ruoyi.graduation.domain.TeacherSuggestion;

/**
 * 教师意见Mapper接口
 * 
 * @author xu
 * @date 2021-06-03
 */
public interface TeacherSuggestionMapper 
{
    /**
     * 查询教师意见
     * 
     * @param id 教师意见ID
     * @return 教师意见
     */
    public TeacherSuggestion selectTeacherSuggestionById(Long id);

    /**
     * 查询教师意见列表
     * 
     * @param teacherSuggestion 教师意见
     * @return 教师意见集合
     */
    public List<TeacherSuggestion> selectTeacherSuggestionList(TeacherSuggestion teacherSuggestion);

    /**
     * 新增教师意见
     * 
     * @param teacherSuggestion 教师意见
     * @return 结果
     */
    public int insertTeacherSuggestion(TeacherSuggestion teacherSuggestion);

    /**
     * 修改教师意见
     * 
     * @param teacherSuggestion 教师意见
     * @return 结果
     */
    public int updateTeacherSuggestion(TeacherSuggestion teacherSuggestion);

    /**
     * 删除教师意见
     * 
     * @param id 教师意见ID
     * @return 结果
     */
    public int deleteTeacherSuggestionById(Long id);

    /**
     * 批量删除教师意见
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTeacherSuggestionByIds(Long[] ids);
}
