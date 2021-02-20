package com.naown.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * 获得随机盐
 * @USER: chenjian
 * @DATE: 2021/2/19 23:54 周五
 **/
public class SaltUtils {
    /**
     * 返回随机盐
     * @param n 返回几位的随机盐 如果为空则返回16位随机盐
     * @return
     */
    public static String getSalt(Integer n){
        if (n==null || n==1){
            return new SecureRandomNumberGenerator().nextBytes(8).toHex();
        }
        Double floor = Math.floor(n / 2);
        return new SecureRandomNumberGenerator().nextBytes(floor.intValue()).toHex();
    }

    public static String getSalt(){
        return getSalt(null);
    }
}
