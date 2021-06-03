package com.ruoyi.graduation.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.graduation.domain.Student;
import com.ruoyi.graduation.domain.Teacher;
import com.ruoyi.graduation.service.IStudentService;
import com.ruoyi.graduation.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.graduation.domain.TeacherStudent;
import com.ruoyi.graduation.service.ITeacherStudentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教师学生关联Controller
 * 
 * @author xu
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/student")
public class TeacherStudentController extends BaseController
{
    @Autowired
    private ITeacherStudentService teacherStudentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IStudentService studentService;

    /**
     * 查询教师学生关联列表
     */
    @PreAuthorize(hasPermi = "graduation:student:list")
    @GetMapping("/list")
    public TableDataInfo list(TeacherStudent teacherStudent)
    {
        startPage();
        List<TeacherStudent> list = teacherStudentService.selectTeacherStudentList(teacherStudent);
        return getDataTable(list);
    }

    /**
     * 导出教师学生关联列表
     */
    @PreAuthorize(hasPermi = "graduation:student:export")
    @Log(title = "教师学生关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeacherStudent teacherStudent) throws IOException
    {
        List<TeacherStudent> list = teacherStudentService.selectTeacherStudentList(teacherStudent);
        ExcelUtil<TeacherStudent> util = new ExcelUtil<TeacherStudent>(TeacherStudent.class);
        util.exportExcel(response, list, "教师学生关联数据");
    }

    /**
     * 获取教师学生关联详细信息
     */
    @PreAuthorize(hasPermi = "graduation:student:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teacherStudentService.selectTeacherStudentById(id));
    }

    /**
     * 选导师事务（新增教师学生关联、student表teacher_id修改、对应教师的teacher表quantity-1
     */
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize(hasPermi = "graduation:student:add")
    @Log(title = "教师学生关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeacherStudent teacherStudent)
    {
        // student表teacher_id修改
        Student student = new Student();
        student.setId(teacherStudent.getStudentId());
        student.setTeacherId(teacherStudent.getTeacherId());
        studentService.updateStudent(student);

        // 对应教师的teacher表quantity-1
        Teacher teacher = new Teacher();
        Long teacherId = teacherStudent.getTeacherId();
        Long quantity = teacherService.selectTeacherById(teacherId).getQuantity();
        --quantity;
        teacher.setId(teacherId);
        teacher.setQuantity(quantity);
        teacherService.updateTeacher(teacher);

        // 新增教师学生关联
        return toAjax(teacherStudentService.insertTeacherStudent(teacherStudent));
    }

    /**
     * 修改教师学生关联
     * 退选事务（teacher_student表减少一条记录，student表teacher_id修改为0，对应教师的teacher表quantity+1。）
     */
    @PreAuthorize(hasPermi = "graduation:student:edit")
    @Log(title = "教师学生关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeacherStudent teacherStudent)
    {
        // student表teacher_id修改为0
        Student student = new Student();
        student.setId(teacherStudent.getStudentId());
        student.setTeacherId(0L);
        studentService.updateStudent(student);

        // 对应教师的teacher表quantity+1
        Teacher teacher = new Teacher();
        Long teacherId = teacherStudent.getTeacherId();
        Long quantity = teacherService.selectTeacherById(teacherId).getQuantity();
        ++quantity;
        teacher.setId(teacherId);
        teacher.setQuantity(quantity);
        teacherService.updateTeacher(teacher);

        // teacher_student表减少一条记录
        return toAjax(teacherStudentService.deleteTeacherStudentById(teacherStudent.getStudentId()));
    }

    /**
     * 删除教师学生关联
     */
    @PreAuthorize(hasPermi = "graduation:student:remove")
    @Log(title = "教师学生关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teacherStudentService.deleteTeacherStudentByIds(ids));
    }
}
