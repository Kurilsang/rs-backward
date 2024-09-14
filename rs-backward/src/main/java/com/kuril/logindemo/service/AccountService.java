package com.kuril.logindemo.service;

import com.kuril.logindemo.pojo.Account;
import com.kuril.logindemo.pojo.Result;

import java.util.List;
public interface AccountService {
    public Result account_list();

    void accountAdd(Account account);

    void accountDeleteById( Integer id);

    void deleteByIds(List<Integer> ids);

    Result getById(int id);

    Account login(Account account);

    void updateById(Account account);

//    Account getByUsername(Account account);
}
