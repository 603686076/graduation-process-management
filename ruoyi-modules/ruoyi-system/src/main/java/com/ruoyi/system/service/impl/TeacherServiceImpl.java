package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.SecurityUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.mapper.TeacherMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TeacherServiceImpl implements ITeacherService
{
    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ISysConfigService configService;

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
     * @param nickName 选择导师任务
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

    /**
     * 导入用户数据
     *
     * @param teacherList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importTeacher(List<Teacher> teacherList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(teacherList) || teacherList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Teacher teacher : teacherList)
        {
            try
            {
                // 验证是否存在这个用户
                Teacher u = teacherMapper.selectTeacherById(teacher.getId());
                if (StringUtils.isNull(u))
                {
//                    user.setPassword(SecurityUtils.encryptPassword(password));
//                    user.setCreateBy(operName);
                    this.insertTeacher(teacher);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + teacher.getId() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
//                    user.setUpdateBy(operName);
                    this.updateTeacher(teacher);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + teacher.getId() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + teacher.getId() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + teacher.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
