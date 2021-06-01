package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.api.domain.dto.StudentUserDTO;
import com.ruoyi.system.api.domain.dto.TeacherUserDTO;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.SecurityUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IStudentService studentService;

    /**
     * 获取用户列表
     */
    @PreAuthorize(hasPermi = "system:user:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:user:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user) throws IOException
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:user:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    // 导入教师
    @Transactional(rollbackFor = Exception.class)
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:user:import")
    @PostMapping("/importDataTeacher")
    public AjaxResult importDataTeacher(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<TeacherUserDTO> util = new ExcelUtil<TeacherUserDTO>(TeacherUserDTO.class);
        List<TeacherUserDTO> teacherUserDTOList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        List<SysUser> userList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        // 设置角色为教师，由于时间原因，这里写死了
        Long[] roleIds = {101L};
        teacherUserDTOList.forEach(teacherUserDTO -> {
            SysUser user = new SysUser();
            user.setUserId(teacherUserDTO.getTeacherId());
            user.setUserName(teacherUserDTO.getUserName());
            user.setNickName(teacherUserDTO.getNickName());
            user.setDeptId(teacherUserDTO.getDeptId());
            user.setPhonenumber(teacherUserDTO.getPhonenumber());
            user.setSex(teacherUserDTO.getSex());
            user.setRoleIds(roleIds);
            userList.add(user);

            Teacher teacher = new Teacher();
            teacher.setId(teacherUserDTO.getTeacherId());
            teacher.setTitle(teacherUserDTO.getTitle());
            teacher.setQuantity(teacherUserDTO.getQuantity());
            teacher.setDescription(teacherUserDTO.getDescription());
            teacherList.add(teacher);
        });
        String messageUser = userService.importUser(userList, updateSupport, operName);
        String messageTeacher = teacherService.importTeacher(teacherList, updateSupport, operName);
        return AjaxResult.success(messageUser);
    }

    // 导入学生
    @Transactional(rollbackFor = Exception.class)
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:user:import")
    @PostMapping("/importDataStudent")
    public AjaxResult importDataStudent(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<StudentUserDTO> util = new ExcelUtil<StudentUserDTO>(StudentUserDTO.class);
        List<StudentUserDTO> studentUserDTOList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        List<SysUser> userList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        // 设置角色为学生，由于时间原因，这里写死了
        Long[] roleIds = {102L};
        studentUserDTOList.forEach(studentUserDTO -> {
            SysUser user = new SysUser();
            user.setUserId(studentUserDTO.getStudentId());
            user.setUserName(studentUserDTO.getUserName());
            user.setNickName(studentUserDTO.getNickName());
            user.setDeptId(studentUserDTO.getDeptId());
            user.setPhonenumber(studentUserDTO.getPhonenumber());
            user.setSex(studentUserDTO.getSex());
            user.setRoleIds(roleIds);
            userList.add(user);

            Student student = new Student();
            student.setId(studentUserDTO.getStudentId());
            student.setClazz(studentUserDTO.getClazz());
            studentList.add(student);
        });
        String messageUser = userService.importUser(userList, updateSupport, operName);
        String messageStudent = studentService.importStudent(studentList, updateSupport, operName);
        return AjaxResult.success(messageUser);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.importTemplateExcel(response, "用户数据");
    }

    // 下载教师模板
    @PostMapping("/importTemplateTeacher")
    public void importTemplateTeacher(HttpServletResponse response) throws IOException
    {
        ExcelUtil<TeacherUserDTO> util = new ExcelUtil<TeacherUserDTO>(TeacherUserDTO.class);
        util.importTemplateExcel(response, "用户数据");
    }

    // 下载学生模板
    @PostMapping("/importTemplateStudent")
    public void importTemplateStudent(HttpServletResponse response) throws IOException
    {
        ExcelUtil<StudentUserDTO> util = new ExcelUtil<StudentUserDTO>(StudentUserDTO.class);
        util.importTemplateExcel(response, "用户数据");
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info/{username}")
    public R<LoginUser> info(@PathVariable("username") String username)
    {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser))
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userService.selectUserById(userId));
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:user:query")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize(hasPermi = "system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize(hasPermi = "system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        // 学生和教师 id 必然不同
        teacherService.deleteTeacherByIds(userIds);
        studentService.deleteStudentByIds(userIds);
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
}
