package com.ruoyi.graduation.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.graduation.domain.Task;
import com.ruoyi.graduation.service.ITaskService;
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
import com.ruoyi.graduation.domain.StudentTask;
import com.ruoyi.graduation.service.IStudentTaskService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 所有任务Controller
 * 
 * @author xu
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/studenttask")
public class StudentTaskController extends BaseController
{
    @Autowired
    private IStudentTaskService studentTaskService;

    @Autowired
    private ITaskService taskService;

    /**
     * 查询所有任务列表
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:list")
    @GetMapping("/list")
    public TableDataInfo list(StudentTask studentTask)
    {
        startPage();

        List<Task> list = taskService.selectStudentTaskList();
        ArrayList<StudentTask> studentTaskList = new ArrayList<>();
        list.forEach(task -> {
            StudentTask studentTask1 = new StudentTask();
            studentTask1.setTaskId(task.getId());
            studentTask1.setFilename(task.getFilename());
            studentTask1.setDescription(task.getDescription());
            studentTaskList.add(studentTask1);
        });
        return getDataTable(studentTaskList);
//        List<StudentTask> list = studentTaskService.selectStudentTaskList(studentTask);
//        return getDataTable(list);
    }

    /**
     * 导出所有任务列表
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:export")
    @Log(title = "所有任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentTask studentTask) throws IOException
    {
        List<StudentTask> list = studentTaskService.selectStudentTaskList(studentTask);
        ExcelUtil<StudentTask> util = new ExcelUtil<StudentTask>(StudentTask.class);
        util.exportExcel(response, list, "所有任务数据");
    }

    /**
     * 获取所有任务详细信息
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(studentTaskService.selectStudentTaskById(id));
    }

    /**
     * 新增所有任务
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:add")
    @Log(title = "所有任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentTask studentTask)
    {
        // 先删除 filename 相同的
        StudentTask studentTask1 = new StudentTask();
        studentTask1.setFilename(studentTask.getFilename());
        List<StudentTask> studentTasks = studentTaskService.selectStudentTaskList(studentTask1);
        studentTasks.forEach(studentTask2 -> {
            studentTaskService.deleteStudentTaskById(studentTask2.getId());
        });
        return toAjax(studentTaskService.insertStudentTask(studentTask));
    }

    /**
     * 修改所有任务
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:edit")
    @Log(title = "所有任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentTask studentTask)
    {
        return toAjax(studentTaskService.updateStudentTask(studentTask));
    }

    /**
     * 删除所有任务
     */
    @PreAuthorize(hasPermi = "graduation:studenttask:remove")
    @Log(title = "所有任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentTaskService.deleteStudentTaskByIds(ids));
    }
}
