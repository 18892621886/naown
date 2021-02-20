package com.naown;

import com.naown.aop.log.mapper.LogMapper;
import com.naown.shiro.entity.User;
import com.naown.shiro.mapper.UserMapper;
import com.naown.utils.SaltUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class NaOwnApplicationTests {

    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;

    @Autowired
    LogMapper logMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User admin = userMapper.findByUserNameRole("admin");
        System.out.println(admin);
        //int info = logMapper.insert(new Log("INFO", System.currentTimeMillis() - (System.currentTimeMillis() + 60)));
        //System.out.println(info);
    }

}
