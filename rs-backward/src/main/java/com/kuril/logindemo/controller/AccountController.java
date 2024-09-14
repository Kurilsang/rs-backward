package com.kuril.logindemo.controller;

import com.kuril.logindemo.pojo.Account;
import com.kuril.logindemo.pojo.Result;
import com.kuril.logindemo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/list")
    public Result accountList()
    {
        return accountService.account_list();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id)
    {
        Result result = accountService.getById(id);
        return result;
    }
    @PostMapping("/insert")
    public Result accountAdd(@RequestBody Account account)
    {
        accountService.accountAdd(account);
        return Result.success();
    }
    @PostMapping("/update")
    public Result updateById(@RequestBody Account account)
    {
        log.info("接受内容{}",account);
        accountService.updateById(account);
        return Result.success();
    }
    // @DeleteMapping("/depts/{id}")
// public Result deleteDept(@PathVariable int id) {
//  log.info("deleteDept:{}",id);
//  accountService.accountDeleteById(id);
//  return Result.success();
// }
    @DeleteMapping("/delete/{ids}")
    public Result delByIds(@PathVariable List<Integer> ids)
    {
        log.info("delete:{}",ids);
        accountService.deleteByIds(ids);
        return Result.success();
    }

}
