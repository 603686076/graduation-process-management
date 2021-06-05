package com.ruoyi.graduation.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.graduation.domain.Student;
import com.ruoyi.graduation.domain.dto.DetermineQuery;
import com.ruoyi.graduation.domain.vo.StudentTopic;
import com.ruoyi.graduation.service.IDetermineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 确定学生课题前端展示controller
@RestController
@RequestMapping("/determine")
public class DetermineController extends BaseController {
    @Autowired
    private IDetermineService determineService;

    /**
     * 通过查询参数查询
     */
    @PreAuthorize(hasPermi = "graduation:determine:list")
    @GetMapping("/list")
    public TableDataInfo list(DetermineQuery determineQuery)
    {
        startPage();
        List<StudentTopic> list = determineService.selectStudentTopic(determineQuery);
        return getDataTable(list);
    }

    /**
     * 获取学生详细信息
     */
    @PreAuthorize(hasPermi = "graduation:determine:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(determineService.selectStudentById(id));
    }

    /**
     * 修改学生课题
     */
    @PreAuthorize(hasPermi = "graduation:determine:edit")
    @Log(title = "学生课题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        return toAjax(determineService.updateStudentTopic(student));
    }
}
