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
import com.ruoyi.graduation.domain.TemplateFileInfo;
import com.ruoyi.graduation.service.ITemplateFileInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 模板文件信息Controller
 * 
 * @author xu
 * @date 2021-06-04
 */
@RestController
@RequestMapping("/templatefileinfo")
public class TemplateFileInfoController extends BaseController
{
    @Autowired
    private ITemplateFileInfoService templateFileInfoService;

    /**
     * 查询模板文件信息列表
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(TemplateFileInfo templateFileInfo)
    {
        startPage();
        List<TemplateFileInfo> list = templateFileInfoService.selectTemplateFileInfoList(templateFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出模板文件信息列表
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:export")
    @Log(title = "模板文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TemplateFileInfo templateFileInfo) throws IOException
    {
        List<TemplateFileInfo> list = templateFileInfoService.selectTemplateFileInfoList(templateFileInfo);
        ExcelUtil<TemplateFileInfo> util = new ExcelUtil<TemplateFileInfo>(TemplateFileInfo.class);
        util.exportExcel(response, list, "模板文件信息数据");
    }

    /**
     * 获取模板文件信息详细信息
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:query")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return AjaxResult.success(templateFileInfoService.selectTemplateFileInfoById(fileId));
    }

    /**
     * 新增模板文件信息
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:add")
    @Log(title = "模板文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TemplateFileInfo templateFileInfo)
    {
        return toAjax(templateFileInfoService.insertTemplateFileInfo(templateFileInfo));
    }

    /**
     * 修改模板文件信息
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:edit")
    @Log(title = "模板文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TemplateFileInfo templateFileInfo)
    {
        return toAjax(templateFileInfoService.updateTemplateFileInfo(templateFileInfo));
    }

    /**
     * 删除模板文件信息
     */
    @PreAuthorize(hasPermi = "graduation:templatefileinfo:remove")
    @Log(title = "模板文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(templateFileInfoService.deleteTemplateFileInfoByIds(fileIds));
    }
}
