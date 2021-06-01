package com.ruoyi.graduation.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.graduation.domain.Teacher;
import com.ruoyi.graduation.service.ITeacherService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 选择导师任务Controller
 * 
 * @author xu
 * @date 2021-05-30
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询选择导师任务列表
     */
    @PreAuthorize(hasPermi = "graduation:teacher:list")
    @GetMapping("/list")
    public TableDataInfo list(String nickName)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(nickName);
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
        List<Teacher> list = teacherService.selectTeacherList(nickName);
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
        return AjaxResult.success(teacherService.selectTeacherById(id));
    }

    /**
     * 新增选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:add")
    @Log(title = "选择导师任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:edit")
    @Log(title = "选择导师任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除选择导师任务
     */
    @PreAuthorize(hasPermi = "graduation:teacher:remove")
    @Log(title = "选择导师任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teacherService.deleteTeacherByIds(ids));
    }
}
