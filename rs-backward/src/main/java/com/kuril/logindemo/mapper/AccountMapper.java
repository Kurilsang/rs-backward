package com.kuril.logindemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuril.logindemo.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    Account selectByUsernameAndPassword(Account account);
    //   @Select("select * from account")
 //   List<Account> selectAll();
}
