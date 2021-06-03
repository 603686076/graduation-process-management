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
import com.ruoyi.graduation.domain.StudentFileInfo;
import com.ruoyi.graduation.service.IStudentFileInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学生文件信息Controller
 * 
 * @author xu
 * @date 2021-06-02
 */
@RestController
@RequestMapping("/studentfileinfo")
public class StudentFileInfoController extends BaseController
{
    @Autowired
    private IStudentFileInfoService studentFileInfoService;

    /**
     * 查询学生文件信息列表
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(StudentFileInfo studentFileInfo)
    {
        startPage();
        List<StudentFileInfo> list = studentFileInfoService.selectStudentFileInfoList(studentFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出学生文件信息列表
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:export")
    @Log(title = "学生文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentFileInfo studentFileInfo) throws IOException
    {
        List<StudentFileInfo> list = studentFileInfoService.selectStudentFileInfoList(studentFileInfo);
        ExcelUtil<StudentFileInfo> util = new ExcelUtil<StudentFileInfo>(StudentFileInfo.class);
        util.exportExcel(response, list, "学生文件信息数据");
    }

    /**
     * 获取学生文件信息详细信息
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:query")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return AjaxResult.success(studentFileInfoService.selectStudentFileInfoById(fileId));
    }

    /**
     * 通过fileName获取学生文件信息详细信息
     */
    @GetMapping(value = "/{fileName}")
    public AjaxResult getInfoByFileName(@PathVariable("fileName") String fileName)
    {
        return AjaxResult.success(studentFileInfoService.selectStudentFileInfoByFileName(fileName));
    }

    /**
     * 新增学生文件信息
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:add")
    @Log(title = "学生文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentFileInfo studentFileInfo)
    {
        // 先删除 file_name 相同的
        StudentFileInfo studentFileInfo1 = new StudentFileInfo();
        studentFileInfo1.setFileName(studentFileInfo.getFileName());
        List<StudentFileInfo> studentFileInfos = studentFileInfoService.selectStudentFileInfoList(studentFileInfo1);
        studentFileInfos.forEach(studentFileInfo2 -> {
            studentFileInfoService.deleteStudentFileInfoById(studentFileInfo2.getFileId());
        });
        return toAjax(studentFileInfoService.insertStudentFileInfo(studentFileInfo));
    }

    /**
     * 修改学生文件信息
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:edit")
    @Log(title = "学生文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentFileInfo studentFileInfo)
    {
        return toAjax(studentFileInfoService.updateStudentFileInfo(studentFileInfo));
    }

    /**
     * 删除学生文件信息
     */
    @PreAuthorize(hasPermi = "graduation:studentfileinfo:remove")
    @Log(title = "学生文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(studentFileInfoService.deleteStudentFileInfoByIds(fileIds));
    }
}
