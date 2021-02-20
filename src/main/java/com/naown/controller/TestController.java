package com.naown.controller;

import com.naown.aop.log.annotation.Log;
import com.naown.utils.SaltUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: chenjian
 * @DATE: 2021/2/12 2:01 周五
 **/

@Controller
public class TestController {

    @Log("测试日志")
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        int i =1/0;
        return "hello";
    }

    /**
     * 假设这是注册
     * @return
     */
    @Log("注册日志")
    @ResponseBody
    @RequestMapping("/register")
    public String register(String username, @RequestParam(defaultValue = "123456") String password){
        System.out.println(password);
        String salt = SaltUtils.getSalt(8);
        System.out.println(salt);
        /** 根据密码生成MD5加随机盐的密钥，散列16次 盐必须要保存到数据库,后面解析需要用到 */
        Md5Hash md5Hash = new Md5Hash(password,salt,16);
        return md5Hash.toHex();
    }

    /**
     * 假设这是功能模块 需要admin角色才能访问
     * @RequiresRoles 表示需要同时具有相同的角色才能访问 logical= Logical.OR添加这个属性则可以表示其中一个角色就可以访问
     * @return
     */
    @Log("功能模块")
    @ResponseBody
    @RequestMapping("/Role")
    @RequiresRoles(value = {"admin"},logical= Logical.OR)
    public String Role(){
        // 业务
        return "";
    }

}
