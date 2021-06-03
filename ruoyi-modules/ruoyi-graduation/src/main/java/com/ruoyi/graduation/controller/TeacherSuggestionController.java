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
import com.ruoyi.graduation.domain.TeacherSuggestion;
import com.ruoyi.graduation.service.ITeacherSuggestionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教师意见Controller
 * 
 * @author xu
 * @date 2021-06-03
 */
@RestController
@RequestMapping("/suggestion")
public class TeacherSuggestionController extends BaseController
{
    @Autowired
    private ITeacherSuggestionService teacherSuggestionService;

    /**
     * 查询教师意见列表
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:list")
    @GetMapping("/list")
    public TableDataInfo list(TeacherSuggestion teacherSuggestion)
    {
        startPage();
        List<TeacherSuggestion> list = teacherSuggestionService.selectTeacherSuggestionList(teacherSuggestion);
        return getDataTable(list);
    }

    /**
     * 导出教师意见列表
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:export")
    @Log(title = "教师意见", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeacherSuggestion teacherSuggestion) throws IOException
    {
        List<TeacherSuggestion> list = teacherSuggestionService.selectTeacherSuggestionList(teacherSuggestion);
        ExcelUtil<TeacherSuggestion> util = new ExcelUtil<TeacherSuggestion>(TeacherSuggestion.class);
        util.exportExcel(response, list, "教师意见数据");
    }

    /**
     * 获取教师意见详细信息
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teacherSuggestionService.selectTeacherSuggestionById(id));
    }

    /**
     * 新增教师意见
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:add")
    @Log(title = "教师意见", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeacherSuggestion teacherSuggestion)
    {
        return toAjax(teacherSuggestionService.insertTeacherSuggestion(teacherSuggestion));
    }

    /**
     * 修改教师意见
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:edit")
    @Log(title = "教师意见", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeacherSuggestion teacherSuggestion)
    {
        return toAjax(teacherSuggestionService.updateTeacherSuggestion(teacherSuggestion));
    }

    /**
     * 删除教师意见
     */
    @PreAuthorize(hasPermi = "graduation:suggestion:remove")
    @Log(title = "教师意见", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teacherSuggestionService.deleteTeacherSuggestionByIds(ids));
    }
}
