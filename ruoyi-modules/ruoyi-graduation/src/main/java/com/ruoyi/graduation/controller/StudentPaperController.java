package com.ruoyi.graduation.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.graduation.domain.StudentTask;
import com.ruoyi.graduation.domain.Task;
import com.ruoyi.graduation.service.IStudentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生论文Controller
 *
 * @author xu
 * @date 2021-06-03
 */
@RestController
@RequestMapping("/studentpapers")
public class StudentPaperController extends BaseController {
    @Autowired
    private IStudentTaskService studentTaskService;

    /**
     * 查询所有任务列表
     */
    @PreAuthorize(hasPermi = "graduation:studentpapers:list")
    @GetMapping("/list")
    public TableDataInfo list(StudentTask studentTask)
    {
        startPage();
        List<StudentTask> list = studentTaskService.selectStudentTaskList(studentTask);
        return getDataTable(list);
    }
}
