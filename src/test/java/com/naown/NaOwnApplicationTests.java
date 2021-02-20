package com.naown;

import com.naown.aop.log.entity.Log;
import com.naown.aop.log.mapper.LogMapper;
import com.naown.utils.SaltUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalTime;

@SpringBootTest
class NaOwnApplicationTests {

    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;

    @Autowired
    LogMapper logMapper;

    @Test
    void contextLoads() {
        System.out.println(SaltUtils.getSalt());
        //int info = logMapper.insert(new Log("INFO", System.currentTimeMillis() - (System.currentTimeMillis() + 60)));
        //System.out.println(info);
    }

}
