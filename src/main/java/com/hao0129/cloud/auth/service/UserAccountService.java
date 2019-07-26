package com.hao0129.cloud.auth.service;

import com.hao0129.cloud.auth.entity.UserAccount;
import com.hao0129.cloud.auth.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserAccount userAccount =userAccountMapper.findUserAccountByUserName(name);
        if (userAccount == null) {
            throw new UsernameNotFoundException("用户名：" + name + "不存在");
        }
        return new User(
                userAccount.getName(),
                userAccount.getPassword(),
                userAccount.getEnabled(),
                userAccount.getAccountNonExpired(),
                userAccount.getCredentialsNonExpired(),
                userAccount.getAccountNonLocked(),
                userAccount.getAuthorities()
        );
    }
}
