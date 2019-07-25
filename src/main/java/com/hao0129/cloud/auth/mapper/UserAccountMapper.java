package com.hao0129.cloud.auth.mapper;

import com.hao0129.cloud.auth.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAccountMapper {
    @Select("select * from t_user_account where name=#{name}")
    public UserAccount findUserAccountByUserName(@Param("name") String name);
}
