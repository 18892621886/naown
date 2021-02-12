package com.naown.controller;

import com.naown.aop.log.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
