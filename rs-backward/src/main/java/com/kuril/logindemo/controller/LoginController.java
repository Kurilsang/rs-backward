package com.kuril.logindemo.controller;

import com.kuril.logindemo.pojo.Account;
import com.kuril.logindemo.pojo.Result;
import com.kuril.logindemo.service.AccountService;
import com.kuril.logindemo.utils.JWTUtitls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class LoginController extends test
{
 @Autowired
 private AccountService accountService;
 @PostMapping("/login")
 public Result login(@RequestBody Account account)
 {
  log.info("登录{}",account);
  Account a = accountService.login(account);
  if(a!=null)
  {
   // 查询到了
   Map<String,Object> map = new HashMap<>();
   map.put("id",a.getId());
   map.put("username",a.getUsername());
   map.put("password",a.getPassword());
   map.put("role",a.getRole());
   String jwt = JWTUtitls.generateToken(map);
   return Result.success(jwt);
  }
  return Result.error("用户名或者密码错误");

 }
 @PostMapping("/register/{method}")
 public Result register(@RequestBody Account account, @PathVariable int method)
 {
  Account a = accountService.login(account);
//  method为0说明调用时候不是按注册按键而是输入账户后检测
  /*method为1为专门给按钮设计的*/
  if(method==0)
  {
   if(a!=null)
   {
    return Result.error("username already exist");
   }
   else
   {
    return Result.success("username can be used");
   }
  }
  else if(method==1)
  {
   if(a==null)
   {
    accountService.accountAdd(account);
   }
   else{
    return Result.error("username already exist");
   }
  }

  return Result.success();
 }

}
