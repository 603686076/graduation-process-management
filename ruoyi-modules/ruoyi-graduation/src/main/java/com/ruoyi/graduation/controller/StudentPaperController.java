package com.ruoyi.graduation.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.graduation.domain.StudentTask;
import com.ruoyi.graduation.domain.Task;
import com.ruoyi.graduation.domain.TeacherStudent;
import com.ruoyi.graduation.service.IStudentTaskService;
import com.ruoyi.graduation.service.ITeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private ITeacherStudentService teacherStudentService;

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

    /**
     * 查询所有选择过这个老师的任务的文件列表
     */
    @GetMapping("/listchosen")
    public TableDataInfo listChosen(TeacherStudent teacherStudent)
    {
        startPage();
        // 通过前端将teacherId包装teacherStudent成的去查所有选择过这个老师的行
        List<TeacherStudent> teacherStudentList = teacherStudentService.selectTeacherStudentList(teacherStudent);
        List<StudentTask> allStudentTaskList = new ArrayList<>();
        teacherStudentList.forEach(teacherStudent1 -> {
            StudentTask studentTask = new StudentTask();
            studentTask.setStudentId(teacherStudent1.getStudentId());
            // 查这个学生的所有任务
            List<StudentTask> studentTaskList = studentTaskService.selectStudentTaskList(studentTask);
            // 将这个学生的所有任务放入allStudentTaskList
            studentTaskList.forEach(studentTask1 -> {
                allStudentTaskList.add(studentTask1);
            });
        });
        return getDataTable(allStudentTaskList);
    }
}
