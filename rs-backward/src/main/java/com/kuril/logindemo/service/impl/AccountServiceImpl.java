package com.kuril.logindemo.service.impl;

import com.kuril.logindemo.mapper.AccountMapper;
import com.kuril.logindemo.pojo.Account;
import com.kuril.logindemo.pojo.Result;
import com.kuril.logindemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Result account_list() {

        return Result.success(accountMapper.selectList(null));
    }

    @Override
    public void accountAdd(Account account) {
        account.setCreateTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        accountMapper.insert(account);
    }

    @Override
    public void accountDeleteById(Integer id) {
        accountMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        accountMapper.deleteByIds(ids);
    }

    @Override
    public Result getById(int id) {
        Result result = Result.success(accountMapper.selectById(id));
        return result;
    }

    @Override
    public Account login(Account account) {
        Account a = accountMapper.selectByUsernameAndPassword(account);
        return a;
    }

    @Override
    public void updateById(Account account) {
        account.setUpdateTime(LocalDateTime.now());
        accountMapper.updateById(account);
    }

//    @Override
//    public Account getByUsername(Account account) {
//        return accountMapper.selectByUsername(account);
//    }
}
