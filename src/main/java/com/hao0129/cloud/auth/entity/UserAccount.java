package com.hao0129.cloud.auth.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserAccount implements UserDetails, Serializable {

    public enum AUTHORITY {
        ROOT, ADMIN, USER
    }

    @JSONField(name = "NAME")
    private String name;                      //用户ID
    @JSONField(name = "PASSWORD", serialize = false)
    private String password;                //用户密码
    @JSONField(name = "AUTHORITY")
    private String authority;               //用户权限
    @JSONField(name = "ENABLED")
    private Boolean enabled;                //是否可用
    @JSONField(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;      //账号没有过期
    @JSONField(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;       //账号没有锁定
    @JSONField(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;  //凭证没有过期

    public static class AuthorityBuilder{
        private JSONArray authorityArray = new JSONArray();
        public AuthorityBuilder addAuthority(AUTHORITY authority){
            this.authorityArray.add(authority);
            return this;
        }
        public String build(){
            return this.authorityArray.toString();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        JSONArray authorityArray = JSON.parseArray(this.authority);
        List<String> authorityList= authorityArray.toJavaList(String.class);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(String authority:authorityList){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+authority));//默认是ROLE_开头
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
