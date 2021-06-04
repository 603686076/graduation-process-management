package com.ruoyi.graduation.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.graduation.domain.Teacher;
import com.ruoyi.graduation.domain.TeacherStudent;
import com.ruoyi.graduation.service.IChooseTeacherService;
import com.ruoyi.graduation.service.ITeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 选择导师任务Controller
 * 
 * @author xu
 * @date 2021-05-30
 */
@RestController
@RequestMapping("/choose")
public class ChooseTeacherController extends BaseController
{
    @Autowired
    private IChooseTeacherService chooseTeacherService;

    @Autowired
    private ITeacherStudentService teacherStudentService;

    @GetMapping("/listTeacherStudent")
    public TableDataInfo listTeacherStudent(TeacherStudent teacherStudent)
    {
        startPage();
        List<TeacherStudent> list = teacherStudentService.selectTeacherStudentList(teacherStudent);
        return getDataTable(list);
    }

    /**
     * 查询选择导师任务列表
     */
    @PreAuthorize(hasPermi = "graduation:teacher:list")
    @GetMapping("/list")
    public TableDataInfo list(String nickName)
    {
        startPage();
        List<Teacher> list = chooseTeacherService.selectTeacherList(nickName);
        return getDataTable(list);
    }

    /**
     * 导出选择导师任务列表
     */
    @PreAuthorize(hasPermi = "graduation:teacher:export")
    @Log(title = "选择导师任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, String nickName) throws IOException
    {
        List<Teacher> list = chooseTeacherService.selectTeacherList(nickName);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        util.exportExcel(response, list, "选择导师任务数据");
    }

    /**
     * 获取选择导师任务详细信息
     */
    @PreAuthorize(hasPermi = "graduation:teacher:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(chooseTeacherService.selectTeacherById(id));
    }

    /**
     * 新增选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:add")
    @Log(title = "选择导师任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(chooseTeacherService.insertTeacher(teacher));
    }

    /**
     * 修改选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:edit")
    @Log(title = "选择导师任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(chooseTeacherService.updateTeacher(teacher));
    }

    /**
     * 删除选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:remove")
    @Log(title = "选择导师任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chooseTeacherService.deleteTeacherByIds(ids));
    }
}
