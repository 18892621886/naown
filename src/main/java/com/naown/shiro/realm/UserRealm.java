package com.naown.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @USER: chenjian
 * @DATE: 2021/2/17 1:10 周三
 **/
@Component
public class UserRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 此处返回的就是在认证SimpleAuthenticationInfo方法中传的第一个参数，比如现在传入的是String，获取到的就是String类型，强转就好
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("执行了授权");
        // 权限列表
        Set<String> permsSet = new HashSet<>();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 设置权限并返回
        simpleAuthorizationInfo.setStringPermissions(permsSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if ("username".equals(token.getUsername())){
            System.out.println("执行了认证");
            // 参数1：用户信息，一般为用户实体类
            // 参数2: MD5+Salt 盐值加密的字符串
            // 参数3: 随机盐
            // 参数4: realm的名字 方法继承至父类
            return new SimpleAuthenticationInfo(token.getPrincipal(),token.getPassword(), ByteSource.Util.bytes("随机盐，应该从数据库中查询"),this.getName());
        }
        System.out.println("执行了认证");
        return null;
    }

    /**
     * 重写父类的默认加密算法，改为MD5加密
     * 设置散列次数为16次，如果需要安全可言设置1024次或者2048
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(16);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
