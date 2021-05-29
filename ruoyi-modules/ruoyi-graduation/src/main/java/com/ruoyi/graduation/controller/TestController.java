package com.ruoyi.graduation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置
 *
 * @author ruoyi
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "毕业管理模块启动成功！";
    }
}
